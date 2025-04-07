package com.fcivillini.store_core.service.impl;

import com.fcivillini.store_core.mapper.OrderMapper;
import com.fcivillini.store_core.mapper.ProductMapper;
import com.fcivillini.store_core.model.Order;
import com.fcivillini.store_core.model.OrderItem;
import com.fcivillini.store_core.model.Product;
import com.fcivillini.store_core.service.OrderService;
import com.fcivillini.store_interface.exc.StoreException;
import com.fcivillini.store_interface.exc.StoreRuntimeException;
import com.fcivillini.store_repository.repository.OrderRepository;
import com.fcivillini.store_repository.repository.ProductRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Slf4j
@Service
@Setter
@Accessors(chain = true)
public class OrderServiceImpl implements OrderService {

    @Value("${spring.data.redis.lock.wait-time}")
    private Long lockWaitTime;
    @Value("${spring.data.redis.lock.lease-time}")
    private Long lockLLeaseTime;

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Order save(Order order) throws StoreException {
        log.info("Creating new order");
        Map<Long, Product> productMap = order.getItems().stream()
                .map(OrderItem::getProduct)
                .map(p -> productMapper.fromDao(productRepository.findById(p.getId()).orElseThrow(() -> new StoreRuntimeException("Product not found", HttpStatus.NOT_FOUND))))
                .collect(Collectors.toMap(Product::getId, p -> p));
        applyStockChanges(productMap, evaluateStockDiff(List.of(), order.getItems()));
        Order saved = orderMapper.fromDao(orderRepository.save(orderMapper.toDao(order.setOrderDate(LocalDateTime.now()))));
        log.info("Order created with ID: {}", saved.getId());
        return saved;
    }

    @Override
    public Order update(Order order) throws StoreException {
        log.info("Start to update order with ID: [{}]", order.getId());

        Order storedOrder = this.findById(order.getId());
        Map<Long, Product> productMap = storedOrder.getItems().stream()
                .map(OrderItem::getProduct)
                .collect(Collectors.toMap(Product::getId, p -> p, (p1, p2) -> p1));
        order.getItems().forEach(orderItem -> {
            Product product = orderItem.getProduct();
            if (productMap.get(product.getId()) == null) {
                productMap.put(
                        product.getId(),
                        productMapper.fromDao(productRepository.findById(product.getId()).orElseThrow(() -> new StoreRuntimeException("Product not found", HttpStatus.NOT_FOUND))))
                ;
            }
        });

        applyStockChanges(productMap, evaluateStockDiff(storedOrder.getItems(), order.getItems()));
        Order saved = orderMapper.fromDao(orderRepository.save(orderMapper.toDao(order.setOrderDate(LocalDateTime.now()))));
        log.info("End to update order with ID: {}", saved.getId());
        return saved;
    }


    @Override
    public void deleteById(Long id) throws StoreException {
        log.info("start to delete order with ID: {}", id);
        Order storedOrder = this.findById(id);
        Map<Long, Product> productMap = storedOrder.getItems().stream()
                .map(OrderItem::getProduct)
                .collect(Collectors.toMap(Product::getId, p -> p));
        applyStockChanges(productMap, evaluateStockDiff(storedOrder.getItems(), asList()));
        orderRepository.deleteById(id);
        log.info("end to delete order with ID: {}", id);
    }

    @Override
    public Order findById(Long id) throws StoreException {
        log.info("start to find order with ID: [{}]", id);
        Order result = orderRepository.findById(id)
                .map(orderMapper::fromDao)
                .orElseThrow(() -> new StoreException("Order not found", HttpStatus.NOT_FOUND));
        log.info("end to find order with ID: [{}]", id);
        return result;
    }

    @Override
    public List<Order> findOrders(LocalDate date, Long userId, String description) throws StoreException {
        log.info("start to find orders with date: [{}], user: [{}], description: [{}]", date, userId, description);
        List<Order> result = orderRepository.findOrders(date, userId, description)
                .stream()
                .map(orderMapper::fromDao)
                .collect(Collectors.toList());
        log.info("end to find orders with date: [{}], user: [{}], description: [{}]", date, userId, description);
        return result;
    }

    protected Map<Long, Integer> evaluateStockDiff(List<OrderItem> oldItems, List<OrderItem> newItems) {
        Map<Long, Integer> stockDiff = new HashMap<>();

        oldItems.forEach(item ->
                stockDiff.merge(item.getProduct().getId(), -item.getQuantity(), Integer::sum)
        );
        newItems.forEach(item ->
                stockDiff.merge(item.getProduct().getId(), item.getQuantity(), Integer::sum)
        );

        return stockDiff;
    }


    protected List<Product> applyStockChanges(final Map<Long, Product> storeProductsMap,
                                              final Map<Long, Integer> stockChanges) throws StoreRuntimeException {
        log.info("Start to apply stock changes to {}", storeProductsMap.keySet());

        List<RLock> acquiredLocks = new ArrayList<>();
        List<Long> sortedProductIds = storeProductsMap.keySet().stream()
                .sorted()
                .toList();

        try {
            sortedProductIds.forEach(productId -> {
                RLock lock = redissonClient.getLock("lock:product:" + productId);
                try {
                    boolean locked = lock.tryLock(lockWaitTime, lockLLeaseTime, TimeUnit.SECONDS);
                    if (!locked) {
                        throw new StoreRuntimeException("Could not acquire lock for product " + productId, HttpStatus.CONFLICT);
                    }
                } catch (InterruptedException e) {
                    throw new StoreRuntimeException("Could not acquire lock for product " + productId + e.getMessage(), HttpStatus.CONFLICT);
                }
                acquiredLocks.add(lock);
            });

            List<Product> updatedProducts = stockChanges.entrySet().stream()
                    .map(entry -> {
                        Long productId = entry.getKey();
                        int diff = entry.getValue();
                        Product product = storeProductsMap.get(productId);
                        int newStock = product.getStock() + diff;
                        if (newStock < 0) {
                            throw new StoreRuntimeException("Insufficient stock for product " + product.getName(), HttpStatus.BAD_REQUEST);
                        }
                        product.setStock(newStock);
                        return product;
                    })
                    .toList();

            return productRepository.saveAll(updatedProducts.stream()
                            .map(productMapper::toDao)
                            .toList()).stream()
                    .map(productMapper::fromDao).toList();
        } finally {
            for (RLock lock : acquiredLocks) {
                try {
                    lock.unlock();
                } catch (Exception e) {
                    log.error("Error releasing lock: {}", e.getMessage(), e);
                }
            }
            log.info("End to apply stock changes. Updated [{}] products", stockChanges.size());
        }
    }


}

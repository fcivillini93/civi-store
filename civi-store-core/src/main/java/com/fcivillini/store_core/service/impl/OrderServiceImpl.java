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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

@Slf4j
@Service
@Setter
@Accessors(chain = true)
public class OrderServiceImpl implements OrderService {

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
                .collect(Collectors.toMap(Product::getId, p -> p));
        applyStockChanges(productMap, evaluateStockDiff(List.of(), order.getItems()));
        Order saved = orderMapper.fromDao(orderRepository.save(orderMapper.toDao(order)));
        log.info("Order created with ID: {}", saved.getId());
        return saved;
    }

    @Override
    public Order update(Order order) throws StoreException {
        log.info("start to update order with ID: [{}]", order.getId());
        Order storedOrder = this.findById(order.getId());
        Map<Long, Product> productMap = Stream.concat(order.getItems().stream(), storedOrder.getItems().stream())
                .map(OrderItem::getProduct)
                .collect(Collectors.toMap(Product::getId, p -> p));
        applyStockChanges(productMap, evaluateStockDiff(storedOrder.getItems(), order.getItems()));
        Order saved = orderMapper.fromDao(orderRepository.save(orderMapper.toDao(order)));
        log.info("end to update order with ID: {}", saved.getId());
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
    public List<Order> findOrders(LocalDate date, String name, String description) throws StoreException {
        log.info("start to find orders with date: [{}], name: [{}], description: [{}]", date, name, description);
        List<Order> result = orderRepository.findOrders(date, name, description)
                .stream()
                .map(orderMapper::fromDao)
                .collect(Collectors.toList());
        log.info("end to find orders with date: [{}], name: [{}], description: [{}]", date, name, description);
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
                                              final Map<Long, Integer> stockChanges) throws StoreException {
        log.info("start to apply stock changes to {}", storeProductsMap.keySet());
        List<Product> productsToSave = new ArrayList<>();
        stockChanges.forEach((productId, diff) -> {
            Product product = storeProductsMap.get(productId);
            int newStock = product.getStock() + diff;
            if (newStock < 0) {
                throw new StoreRuntimeException("Insufficient stock for product " + product.getName(), HttpStatus.BAD_REQUEST);
            }
            product.setStock(newStock);
            productsToSave.add(product);

        });
        List<Product> result = productRepository.saveAll(productsToSave.stream()
                        .map(product -> productMapper.toDao(product)).toList()).stream()
                .map(p -> productMapper.fromDao(p)).collect(Collectors.toList());
        log.info("end to apply stock changes. Update [{}] products", result.size());
        return result;
    }


}

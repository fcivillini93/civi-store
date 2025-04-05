package com.fcivillini.store_core.service.impl;

import com.fcivillini.store_core.mapper.OrderMapper;
import com.fcivillini.store_core.mapper.ProductMapper;
import com.fcivillini.store_core.model.Order;
import com.fcivillini.store_core.model.OrderItem;
import com.fcivillini.store_core.model.Product;
import com.fcivillini.store_interface.exc.StoreException;
import com.fcivillini.store_interface.exc.StoreRuntimeException;
import com.fcivillini.store_repository.dao.OrderDao;
import com.fcivillini.store_repository.dao.OrderItemDao;
import com.fcivillini.store_repository.dao.ProductDao;
import com.fcivillini.store_repository.repository.OrderRepository;
import com.fcivillini.store_repository.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(orderRepository, productRepository, orderMapper, productMapper);
    }

    @Test
    void testFindById() throws StoreException {
        Long id = 1L;
        OrderDao dao = new OrderDao();
        Order order = new Order();

        when(orderRepository.findById(id)).thenReturn(Optional.of(dao));
        when(orderMapper.fromDao(dao)).thenReturn(order);

        Order result = orderService.findById(id);

        assertNotNull(result);
        assertEquals(order, result);
        verify(orderRepository).findById(id);
        verify(orderMapper).fromDao(dao);
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        StoreException ex = assertThrows(StoreException.class, () -> orderService.findById(id));

        assertEquals(HttpStatus.NOT_FOUND, ex.getHttpStatus());
        verify(orderRepository).findById(id);
        verify(orderMapper, never()).fromDao(any());
    }

    @Test
    void testFindOrders() throws StoreException {
        LocalDate date = LocalDate.now();
        Long userId = 1L;
        String desc = "desc";

        OrderDao dao = new OrderDao();
        Order order = new Order();

        when(orderRepository.findOrders(date, userId, desc)).thenReturn(List.of(dao));
        when(orderMapper.fromDao(dao)).thenReturn(order);

        List<Order> result = orderService.findOrders(date, userId, desc);

        assertEquals(1, result.size());
        assertTrue(result.contains(order));
        verify(orderRepository).findOrders(date, userId, desc);
        verify(orderMapper).fromDao(dao);
    }

    @Test
    void testSave() throws StoreException {
        Product product = new Product().setId(1L).setStock(10);
        OrderItem item = new OrderItem().setProduct(product).setQuantity(2);
        Order order = new Order().setItems(List.of(item));
        OrderDao dao = new OrderDao();
        OrderDao savedDao = new OrderDao();
        Order savedOrder = new Order();

        when(orderMapper.toDao(order)).thenReturn(dao);
        when(orderRepository.save(dao)).thenReturn(savedDao);
        when(orderMapper.fromDao(savedDao)).thenReturn(savedOrder);
        when(productMapper.toDao(any())).thenReturn(new ProductDao());
        when(productMapper.fromDao(any())).thenReturn(product);
        when(productRepository.saveAll(any())).thenReturn(List.of(new ProductDao()));

        Order result = orderService.save(order);

        assertEquals(savedOrder, result);
        verify(orderRepository).save(dao);
        verify(productRepository).saveAll(any());
    }

    @Test
    void testUpdate() throws StoreException {
        Long id = 1L;

        Product p1 = new Product().setId(1L).setStock(20);
        OrderItem oldItem = new OrderItem().setProduct(p1).setQuantity(2);
        Order oldOrder = new Order().setId(id).setItems(List.of(oldItem));

        Product p2 = new Product().setId(1L).setStock(20);
        OrderItem newItem = new OrderItem().setProduct(p2).setQuantity(4);
        Order newOrder = new Order().setId(id).setItems(List.of(newItem));

        ProductDao pDao = new ProductDao().setId(1L).setStock(20);
        OrderItemDao oldItemDao = new OrderItemDao().setProduct(pDao).setQuantity(2);
        OrderItemDao newItemDao = new OrderItemDao().setProduct(pDao).setQuantity(4);
        OrderDao dao = new OrderDao().setItems(List.of(oldItemDao));
        OrderDao savedDao = new OrderDao().setItems(List.of(newItemDao));
        Order updatedOrder = new Order();

        when(orderRepository.findById(id)).thenReturn(Optional.of(dao));
        when(orderMapper.fromDao(dao)).thenReturn(oldOrder);
        when(orderMapper.toDao(newOrder)).thenReturn(dao);
        when(orderRepository.save(dao)).thenReturn(savedDao);
        when(orderMapper.fromDao(savedDao)).thenReturn(updatedOrder);
        when(productMapper.toDao(any())).thenReturn(new ProductDao());
        when(productMapper.fromDao(any())).thenReturn(p2);
        when(productRepository.saveAll(any())).thenReturn(List.of(new ProductDao()));

        Order result = orderService.update(newOrder);

        assertEquals(updatedOrder, result);
        verify(orderRepository).save(dao);
    }

    @Test
    void testDeleteById() throws StoreException {
        Long id = 1L;

        Product product = new Product().setId(1L).setStock(5);
        OrderItem item = new OrderItem().setProduct(product).setQuantity(3);
        Order order = new Order().setItems(List.of(item));

        OrderDao dao = new OrderDao();

        when(orderRepository.findById(id)).thenReturn(Optional.of(dao));
        when(orderMapper.fromDao(dao)).thenReturn(order);
        when(productMapper.toDao(any())).thenReturn(new ProductDao());
        when(productMapper.fromDao(any())).thenReturn(product);
        when(productRepository.saveAll(any())).thenReturn(List.of(new ProductDao()));

        orderService.deleteById(id);

        verify(orderRepository).deleteById(id);
    }

    @Test
    void testApplyStockChanges_shouldThrowIfStockNegative() {
        Product product = new Product().setId(1L).setStock(1).setName("Test");
        Map<Long, Product> productMap = Map.of(1L, product);
        Map<Long, Integer> diff = Map.of(1L, -2);

        StoreRuntimeException ex = assertThrows(StoreRuntimeException.class,
                () -> orderService.applyStockChanges(productMap, diff));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
    }

    @Test
    void testEvaluateStockDiff() {
        OrderItem oldItem = new OrderItem().setProduct(new Product().setId(1L)).setQuantity(5);
        OrderItem newItem = new OrderItem().setProduct(new Product().setId(1L)).setQuantity(3);

        Map<Long, Integer> result = orderService.evaluateStockDiff(List.of(oldItem), List.of(newItem));

        assertEquals(-2, result.get(1L));
    }
}

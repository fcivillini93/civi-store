package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_repository.dao.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderRepositoryRdbmsTest extends AbstractRepositoryRdbmsTest {

    @Test
    void testSaveAndFindById() {
        UserDao userDao = userRepository.save(new UserDao()
                .setName("user")
                .setEmail("email"));

        ProductDao productDao = productRepository.save(new ProductDao()
                .setName("Laptop")
                .setDescription("A powerful laptop")
                .setPrice(new BigDecimal("1500.00"))
                .setStock(10));

        OrderDao order = new OrderDao()
                .setUser(userDao)
                .setDescription("Test Order")
                .setOrderDate(LocalDateTime.of(2023, 10, 1, 12, 0))
                .setStatus(OrderStatusDao.PENDING)
                .setItems(asList(
                        new OrderItemDao().setProduct(productDao).setQuantity(1)
                ));

        OrderDao savedOrder = orderRepository.save(order);
        Optional<OrderDao> foundOrder = orderRepository.findById(savedOrder.getId());

        assertTrue(foundOrder.isPresent());
        assertEquals(savedOrder, foundOrder.get());
    }

    @Test
    void testDeleteById() {
        UserDao userDao = userRepository.save(new UserDao()
                .setName("user")
                .setEmail("email"));

        ProductDao productDao = productRepository.save(new ProductDao()
                .setName("Monitor")
                .setDescription("HD Monitor")
                .setPrice(new BigDecimal("200.00"))
                .setStock(5));

        OrderDao order = new OrderDao()
                .setUser(userDao)
                .setDescription("To be deleted")
                .setOrderDate(LocalDateTime.now())
                .setStatus(OrderStatusDao.PENDING)
                .setItems(asList(
                        new OrderItemDao().setProduct(productDao).setQuantity(2)
                ));

        OrderDao savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();
        orderRepository.deleteById(orderId);

        Optional<OrderDao> result = orderRepository.findById(orderId);
        assertTrue(result.isEmpty());
    }

    @Test
    @SneakyThrows
    void testFindOrders_ByDateUserAndDescription() {
        UserDao userDao = userRepository.save(new UserDao()
                .setName("Mario")
                .setEmail("mario@example.com"));

        ProductDao productDao = productRepository.save(new ProductDao()
                .setName("Tablet")
                .setDescription("Android Tablet")
                .setPrice(new BigDecimal("300.00"))
                .setStock(15));

        LocalDateTime orderDate = LocalDateTime.of(2024, 4, 5, 10, 30);

        OrderDao order = new OrderDao()
                .setUser(userDao)
                .setDescription("Ordine personale")
                .setOrderDate(orderDate)
                .setStatus(OrderStatusDao.PENDING)
                .setItems(asList(
                        new OrderItemDao().setProduct(productDao).setQuantity(1)
                ));

        orderRepository.save(order);

        List<OrderDao> results = orderRepository.findOrders(
                orderDate.toLocalDate(),
                userDao.getId(),
                "personale"
        );

        assertEquals(1, results.size());
        assertEquals("Ordine personale", results.get(0).getDescription());
    }

    @Test
    @SneakyThrows
    void testFindOrders_WithNullFilters() {
        UserDao userDao = userRepository.save(new UserDao()
                .setName("Lucia")
                .setEmail("lucia@example.com"));

        ProductDao productDao = productRepository.save(new ProductDao()
                .setName("Phone")
                .setDescription("Smartphone")
                .setPrice(new BigDecimal("600.00"))
                .setStock(8));

        OrderDao order1 = new OrderDao()
                .setUser(userDao)
                .setDescription("Order 1")
                .setOrderDate(LocalDateTime.now())
                .setStatus(OrderStatusDao.PENDING)
                .setItems(asList(
                        new OrderItemDao().setProduct(productDao).setQuantity(1)
                ));

        OrderDao order2 = new OrderDao()
                .setUser(userDao)
                .setDescription("Order 2")
                .setOrderDate(LocalDateTime.now())
                .setStatus(OrderStatusDao.PENDING)
                .setItems(asList(
                        new OrderItemDao().setProduct(productDao).setQuantity(2)
                ));

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<OrderDao> results = orderRepository.findOrders(null, null, null);

        assertTrue(results.size() >= 2);
    }
}
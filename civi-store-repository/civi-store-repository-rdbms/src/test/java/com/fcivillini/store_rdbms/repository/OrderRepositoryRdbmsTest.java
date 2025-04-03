package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_repository.dao.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
}
package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.configuration.CoreMapperConfiguration;
import com.fcivillini.store_core.model.*;
import com.fcivillini.store_interface.dto.*;
import com.fcivillini.store_repository.dao.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = CoreMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class OrderMapperTest {

    @Autowired
    private OrderMapper mapper;

    @Test
    void test_fromDao() {
        assertEquals(new Order()
                        .setId(1L)
                        .setDescription("description")
                        .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                        .setStatus(OrderStatus.PENDING)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                        .setUser(new User().setId(2L))
                        .setItems(asList(new OrderItem().setId(3L).setProduct(new Product().setId(4L)))),
                mapper.fromDao(new OrderDao()
                        .setId(1L)
                        .setDescription("description")
                        .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                        .setStatus(OrderStatusDao.PENDING)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                        .setUser(new UserDao().setId(2L))
                        .setItems(asList(new OrderItemDao().setId(3L).setProduct(new ProductDao().setId(4L))))));

    }


    @Test
    void test_fromDto() {
        assertEquals(new Order()
                        .setId(1L)
                        .setDescription("description")
                        .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                        .setStatus(OrderStatus.PENDING)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                        .setUser(new User().setId(2L))
                        .setItems(asList(new OrderItem().setId(3L).setProduct(new Product().setId(4L)))),
                mapper.fromDto(new OrderDto()
                        .setId(1L)
                        .setDescription("description")
                        .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                        .setStatus(OrderStatusDto.PENDING)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                        .setUser(new UserDto().setId(2L))
                        .setItems(asList(new OrderItemDto().setId(3L).setProduct(new ProductDto().setId(4L))))));

    }

    @Test
    void test_toDao() {
        assertEquals(
                new OrderDao()
                        .setId(1L)
                        .setDescription("description")
                        .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                        .setStatus(OrderStatusDao.PENDING)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                        .setUser(new UserDao().setId(2L))
                        .setItems(asList(new OrderItemDao().setId(3L).setProduct(new ProductDao().setId(4L)))),
                mapper.toDao(new Order()
                        .setId(1L)
                        .setDescription("description")
                        .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                        .setStatus(OrderStatus.PENDING)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                        .setUser(new User().setId(2L))
                        .setItems(asList(new OrderItem().setId(3L).setProduct(new Product().setId(4L))))));

    }

    @Test
    void test_toDto() {
        assertEquals(
                new OrderDto()
                        .setId(1L)
                        .setDescription("description")
                        .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                        .setStatus(OrderStatusDto.PENDING)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                        .setUser(new UserDto().setId(2L))
                        .setItems(asList(new OrderItemDto().setId(3L).setProduct(new ProductDto().setId(4L)))),
                mapper.toDto(new Order()
                        .setId(1L)
                        .setDescription("description")
                        .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                        .setStatus(OrderStatus.PENDING)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                        .setUser(new User().setId(2L))
                        .setItems(asList(new OrderItem().setId(3L).setProduct(new Product().setId(4L))))));

    }


}
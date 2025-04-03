package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.configuration.RdbmsMapperConfiguration;
import com.fcivillini.store_rdbms.entity.OrderItemRdbms;
import com.fcivillini.store_rdbms.entity.OrderRdbms;
import com.fcivillini.store_rdbms.entity.ProductRdbms;
import com.fcivillini.store_rdbms.entity.UserRdbms;
import com.fcivillini.store_rdbms.model.OrderStatusRdbms;
import com.fcivillini.store_repository.dao.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = RdbmsMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class OrderMapperRdbmsTest {

    @Autowired
    private OrderMapperRdbms mapper;

    @Test
    void test_toRdbms() {
        OrderDao dao = new OrderDao()
                .setId(1L)
                .setDescription("description")
                .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                .setStatus(OrderStatusDao.PENDING)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                .setUser(new UserDao().setId(2L))
                .setItems(asList(new OrderItemDao().setId(3L).setProduct(new ProductDao().setId(4L))));

        List<OrderItemRdbms> itemsRdbms = new ArrayList<>();
        OrderRdbms rdbms = new OrderRdbms()
                .setId(1L)
                .setDescription("description")
                .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                .setStatus(OrderStatusRdbms.PENDING)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                .setUser(new UserRdbms().setId(2L))
                .setItems(itemsRdbms);
        OrderItemRdbms expectedItem = new OrderItemRdbms().setId(3L).setProduct(new ProductRdbms().setId(4L)).setOrder(rdbms);
        itemsRdbms.add(expectedItem);

        OrderRdbms result = mapper.toRdbms(dao);
        assertEquals(rdbms.getId(), result.getId());
        assertEquals(rdbms.getDescription(), result.getDescription());
        assertEquals(rdbms.getOrderDate(), result.getOrderDate());
        assertEquals(rdbms.getStatus(), result.getStatus());
        assertEquals(rdbms.getCreatedDate(), result.getCreatedDate());
        assertEquals(rdbms.getUpdatedDate(), result.getUpdatedDate());
        assertEquals(rdbms.getUser().getId(), result.getUser().getId());
        assertEquals(rdbms.getItems().size(), result.getItems().size());
        OrderItemRdbms resultItem = result.getItems().get(0);
        assertEquals(expectedItem.getId(), resultItem.getId());
        assertEquals(expectedItem.getProduct().getId(), resultItem.getProduct().getId());
        assertEquals(expectedItem.getOrder().getId(), resultItem.getOrder().getId());

    }

    @Test
    void test_fromRdbms() {
        List<OrderItemRdbms> itemsRdbms = new ArrayList<>();
        OrderRdbms rdbms = new OrderRdbms()
                .setId(1L)
                .setDescription("description")
                .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                .setStatus(OrderStatusRdbms.PENDING)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                .setUser(new UserRdbms().setId(2L))
                .setItems(itemsRdbms)
                ;
        itemsRdbms.add(new OrderItemRdbms().setId(3L).setProduct(new ProductRdbms().setId(4L)).setOrder(rdbms));

        assertEquals(new OrderDao()
                        .setId(1L)
                        .setDescription("description")
                        .setOrderDate(LocalDateTime.of(2020, 1, 1, 0, 20))
                        .setStatus(OrderStatusDao.PENDING)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                        .setUser(new UserDao().setId(2L))
                        .setItems(asList(new OrderItemDao().setId(3L).setProduct(new ProductDao().setId(4L))))
                , mapper.fromRdbms(rdbms));
    }
}
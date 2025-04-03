package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.configuration.RdbmsMapperConfiguration;
import com.fcivillini.store_rdbms.entity.OrderItemRdbms;
import com.fcivillini.store_rdbms.entity.OrderRdbms;
import com.fcivillini.store_rdbms.entity.ProductRdbms;
import com.fcivillini.store_repository.dao.OrderItemDao;
import com.fcivillini.store_repository.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = RdbmsMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class OrderItemMapperRdbmsTest {

    @Autowired
    private OrderItemMapperRdbms mapper;

    @Test
    void test_toRdbms() {
        OrderItemDao dao = new OrderItemDao()
                .setId(1L)
                .setQuantity(100)
                .setProduct(new ProductDao().setId(3L))
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        OrderItemRdbms rdbms = new OrderItemRdbms()
                .setId(1L)
                .setQuantity(100)
                .setOrder(new OrderRdbms().setId(2L))
                .setProduct(new ProductRdbms().setId(3L))
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        OrderItemRdbms result = mapper.toRdbms(dao);
        assertEquals(rdbms.getId(), result.getId());
        assertEquals(rdbms.getQuantity(), result.getQuantity());
        assertEquals(rdbms.getProduct().getId(), result.getProduct().getId());
        assertEquals(rdbms.getCreatedDate(), result.getCreatedDate());
        assertEquals(rdbms.getUpdatedDate(), result.getUpdatedDate());

    }

    @Test
    void test_fromRdbms() {
        OrderItemRdbms rdbms = new OrderItemRdbms()
                .setId(1L)
                .setQuantity(100)
                .setOrder(new OrderRdbms().setId(2L))
                .setProduct(new ProductRdbms().setId(3L))
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        assertEquals(new OrderItemDao()
                        .setId(1L)
                        .setQuantity(100)
                        .setProduct(new ProductDao().setId(3L))
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                , mapper.fromRdbms(rdbms));
    }
}
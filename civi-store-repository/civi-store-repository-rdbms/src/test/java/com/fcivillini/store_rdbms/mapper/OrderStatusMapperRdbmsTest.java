package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.configuration.RdbmsMapperConfiguration;
import com.fcivillini.store_rdbms.entity.UserRdbms;
import com.fcivillini.store_rdbms.model.OrderStatusRdbms;
import com.fcivillini.store_repository.dao.OrderStatusDao;
import com.fcivillini.store_repository.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = RdbmsMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class OrderStatusMapperRdbmsTest {

    @Autowired
    private OrderStatusMapperRdbms mapper;

    @Test
    void test_toRdbms() {
        assertEquals(OrderStatusRdbms.PENDING, mapper.toRdbms(OrderStatusDao.PENDING));
        assertEquals(OrderStatusRdbms.CONFIRMED, mapper.toRdbms(OrderStatusDao.CONFIRMED));
        assertEquals(OrderStatusRdbms.CANCELLED, mapper.toRdbms(OrderStatusDao.CANCELLED));

    }

    @Test
    void test_fromRdbms() {
        assertEquals(OrderStatusDao.PENDING, mapper.fromRdbms(OrderStatusRdbms.PENDING));
        assertEquals(OrderStatusDao.CONFIRMED, mapper.fromRdbms(OrderStatusRdbms.CONFIRMED));
        assertEquals(OrderStatusDao.CANCELLED, mapper.fromRdbms(OrderStatusRdbms.CANCELLED));
    }
}
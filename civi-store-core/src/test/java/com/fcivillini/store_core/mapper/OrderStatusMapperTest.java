package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.configuration.CoreMapperConfiguration;
import com.fcivillini.store_core.model.OrderStatus;
import com.fcivillini.store_interface.dto.OrderStatusDto;
import com.fcivillini.store_repository.dao.OrderStatusDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = CoreMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class OrderStatusMapperTest {

    @Autowired
    private OrderStatusMapper mapper;

    @Test
    void test_fromDao() {
        assertEquals(OrderStatus.PENDING, mapper.fromDao(OrderStatusDao.PENDING));
        assertEquals(OrderStatus.CONFIRMED, mapper.fromDao(OrderStatusDao.CONFIRMED));
        assertEquals(OrderStatus.CANCELLED, mapper.fromDao(OrderStatusDao.CANCELLED));
    }

    @Test
    void test_fromDto() {
        assertEquals(OrderStatus.PENDING, mapper.fromDto(OrderStatusDto.PENDING));
        assertEquals(OrderStatus.CONFIRMED, mapper.fromDto(OrderStatusDto.CONFIRMED));
        assertEquals(OrderStatus.CANCELLED, mapper.fromDto(OrderStatusDto.CANCELLED));
    }

    @Test
    void test_toDto() {
        assertEquals(OrderStatusDto.PENDING, mapper.toDto(OrderStatus.PENDING));
        assertEquals(OrderStatusDto.CONFIRMED, mapper.toDto(OrderStatus.CONFIRMED));
        assertEquals(OrderStatusDto.CANCELLED, mapper.toDto(OrderStatus.CANCELLED));
    }
}
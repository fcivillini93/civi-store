package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.configuration.CoreMapperConfiguration;
import com.fcivillini.store_core.model.OrderItem;
import com.fcivillini.store_core.model.Product;
import com.fcivillini.store_interface.dto.OrderItemDto;
import com.fcivillini.store_interface.dto.ProductDto;
import com.fcivillini.store_repository.dao.OrderItemDao;
import com.fcivillini.store_repository.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = CoreMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class OrderItemMapperTest {

    @Autowired
    private OrderItemMapper mapper;

    @Test
    void test_fromDao() {
        assertEquals(new OrderItem()
                        .setId(1L)
                        .setQuantity(100)
                        .setProduct(new Product().setId(3L))
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)),
                mapper.fromDao(new OrderItemDao()
                        .setId(1L)
                        .setQuantity(100)
                        .setProduct(new ProductDao().setId(3L))
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))));

    }

    @Test
    void test_fromDto() {
        assertEquals(new OrderItem()
                        .setId(1L)
                        .setQuantity(100)
                        .setProduct(new Product().setId(3L))
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)),
                mapper.fromDto(new OrderItemDto()
                        .setId(1L)
                        .setQuantity(100)
                        .setProduct(new ProductDto().setId(3L))
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))));

    }

    @Test
    void test_toDao() {
        assertEquals(new OrderItemDao()
                        .setId(1L)
                        .setQuantity(100)
                        .setProduct(new ProductDao().setId(3L))
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)),
                mapper.toDao(new OrderItem()
                        .setId(1L)
                        .setQuantity(100)
                        .setProduct(new Product().setId(3L))
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))));

    }

    @Test
    void test_toDto() {
        assertEquals(new OrderItemDto()
                        .setId(1L)
                        .setQuantity(100)
                        .setProduct(new ProductDto().setId(3L))
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)),
                mapper.toDto(new OrderItem()
                        .setId(1L)
                        .setQuantity(100)
                        .setProduct(new Product().setId(3L))
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))));

    }

}
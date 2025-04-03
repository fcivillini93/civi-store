package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.configuration.RdbmsMapperConfiguration;
import com.fcivillini.store_rdbms.entity.ProductRdbms;
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
class ProductMapperRdbmsTest {

    @Autowired
    private ProductMapperRdbms mapper;

    @Test
    void test_toRdbms() {
        ProductDao dao = new ProductDao()
                .setId(1L)
                .setName("user")
                .setDescription("description")
                .setPrice(new BigDecimal("10.00"))
                .setStock(100)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        ProductRdbms rdbms = new ProductRdbms()
                .setId(1L)
                .setName("user")
                .setDescription("description")
                .setPrice(new BigDecimal("10.00"))
                .setStock(100)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        ProductRdbms result = mapper.toRdbms(dao);
        assertEquals(rdbms.getId(), result.getId());
        assertEquals(rdbms.getName(), result.getName());
        assertEquals(rdbms.getDescription(), result.getDescription());
        assertEquals(rdbms.getStock(), result.getStock());
        assertEquals(rdbms.getPrice(), result.getPrice());
        assertEquals(rdbms.getCreatedDate(), result.getCreatedDate());
        assertEquals(rdbms.getUpdatedDate(), result.getUpdatedDate());

    }

    @Test
    void test_fromRdbms() {
        ProductRdbms rdbms = new ProductRdbms()
                .setId(1L)
                .setName("user")
                .setDescription("description")
                .setPrice(new BigDecimal("10.00"))
                .setStock(100)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        assertEquals(new ProductDao()
                        .setId(1L)
                        .setName("user")
                        .setDescription("description")
                        .setPrice(new BigDecimal("10.00"))
                        .setStock(100)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                , mapper.fromRdbms(rdbms));
    }
}
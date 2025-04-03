package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.configuration.CoreMapperConfiguration;
import com.fcivillini.store_core.model.Product;
import com.fcivillini.store_interface.dto.ProductDto;
import com.fcivillini.store_repository.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = CoreMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class ProductMapperTest {

    @Autowired
    private ProductMapper mapper;

    @Test
    void test_fromDao() {
        ProductDao dao = new ProductDao()
                .setId(1L)
                .setName("user")
                .setDescription("description")
                .setPrice(new BigDecimal("10.00"))
                .setStock(100)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        assertEquals(new Product()
                .setId(1L)
                .setName("user")
                .setDescription("description")
                .setPrice(new BigDecimal("10.00"))
                .setStock(100)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)), mapper.fromDao(dao));

    }

    @Test
    void test_fromDto() {
        ProductDto dto = new ProductDto()
                .setId(1L)
                .setName("user")
                .setDescription("description")
                .setPrice(new BigDecimal("10.00"))
                .setStock(100)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        assertEquals(new Product()
                .setId(1L)
                .setName("user")
                .setDescription("description")
                .setPrice(new BigDecimal("10.00"))
                .setStock(100)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)), mapper.fromDto(dto));

    }

    @Test
    void test_toDao() {
        Product rdbms = new Product()
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
                , mapper.toDao(rdbms));
    }

    @Test
    void test_toDto() {
        Product rdbms = new Product()
                .setId(1L)
                .setName("user")
                .setDescription("description")
                .setPrice(new BigDecimal("10.00"))
                .setStock(100)
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        assertEquals(new ProductDto()
                        .setId(1L)
                        .setName("user")
                        .setDescription("description")
                        .setPrice(new BigDecimal("10.00"))
                        .setStock(100)
                        .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                , mapper.toDto(rdbms));
    }
}
package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_repository.dao.ProductDao;
import com.fcivillini.store_repository.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductRepositoryRdbmsTest extends AbstractRepositoryRdbmsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndFindById() {
        ProductDao product = new ProductDao()
                .setName("Laptop")
                .setDescription("A powerful laptop")
                .setPrice(new BigDecimal("1500.00"))
                .setStock(10);

        ProductDao savedProduct = productRepository.save(product);
        Optional<ProductDao> foundProduct = productRepository.findById(savedProduct.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals(savedProduct, foundProduct.get());
    }
}
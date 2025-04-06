package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_repository.dao.ProductDao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryRdbmsTest extends AbstractRepositoryRdbmsTest {

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

    @Test
    void testSaveAllAndFindAll() {
        ProductDao p1 = new ProductDao()
                .setName("Phone")
                .setDescription("Smartphone")
                .setPrice(new BigDecimal("800.00"))
                .setStock(5);

        ProductDao p2 = new ProductDao()
                .setName("Tablet")
                .setDescription("Android tablet")
                .setPrice(new BigDecimal("600.00"))
                .setStock(8);

        List<ProductDao> savedProducts = productRepository.saveAll(asList(p1, p2));
        List<ProductDao> allProducts = productRepository.findAll();

        assertTrue(allProducts.containsAll(savedProducts));
        assertEquals(2, savedProducts.size());
    }

    @Test
    void testDeleteById() {
        ProductDao product = new ProductDao()
                .setName("Camera")
                .setDescription("DSLR camera")
                .setPrice(new BigDecimal("1200.00"))
                .setStock(3);

        ProductDao savedProduct = productRepository.save(product);
        Long productId = savedProduct.getId();

        productRepository.deleteById(productId);
        Optional<ProductDao> deletedProduct = productRepository.findById(productId);

        assertTrue(deletedProduct.isEmpty());
    }
}
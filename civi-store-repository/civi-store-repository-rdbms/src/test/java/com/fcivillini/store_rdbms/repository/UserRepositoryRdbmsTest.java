package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_repository.dao.ProductDao;
import com.fcivillini.store_repository.dao.UserDao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryRdbmsTest extends AbstractRepositoryRdbmsTest {

    @Test
    void testSaveAndFindById() {
        UserDao product = new UserDao()
                .setName("user")
                .setEmail("email");

        UserDao savedProduct = userRepository.save(product);
        Optional<UserDao> foundProduct = userRepository.findById(savedProduct.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals(savedProduct, foundProduct.get());
    }
}
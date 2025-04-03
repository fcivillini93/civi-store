package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_rdbms.repository.jpa.ProductRepositoryJpa;
import com.fcivillini.store_repository.dao.ProductDao;
import com.fcivillini.store_repository.repository.ProductRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@Setter
@Accessors(chain = true)
public class ProductRepositoryRdbms implements ProductRepository {

    @Autowired
    private ProductRepositoryJpa productRepositoryRdbms;

    @Override
    public Optional<ProductDao> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductDao save(ProductDao order) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

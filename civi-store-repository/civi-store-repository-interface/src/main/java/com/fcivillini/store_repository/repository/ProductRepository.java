package com.fcivillini.store_repository.repository;

import com.fcivillini.store_repository.dao.ProductDao;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<ProductDao> findById(Long id);

    ProductDao save(ProductDao order);

    List<ProductDao> saveAll(List<ProductDao> productList);

    void deleteById(Long id);

    List<ProductDao> findAll();
}

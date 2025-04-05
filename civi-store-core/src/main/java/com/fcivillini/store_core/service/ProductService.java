package com.fcivillini.store_core.service;


import com.fcivillini.store_core.model.Product;
import com.fcivillini.store_interface.exc.StoreException;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product update(Product product) throws StoreException;

    Product findById(Long id) throws StoreException;

    List<Product> findAll();

    void deleteById(Long id);

}

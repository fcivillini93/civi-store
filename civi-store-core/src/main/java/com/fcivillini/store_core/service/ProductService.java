package com.fcivillini.store_core.service;

import com.fcivillini.store_core.model.Product;
import com.fcivillini.store_interface.exc.StoreException;

import java.util.List;

/**
 * Service interface for managing products.
 */
public interface ProductService {

    /**
     * Saves a new product.
     *
     * @param product the product to save
     * @return the saved product
     */
    Product save(Product product);

    /**
     * Updates an existing product.
     *
     * @param product the product to update
     * @return the updated product
     * @throws StoreException if the product cannot be updated
     */
    Product update(Product product) throws StoreException;

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product to find
     * @return the found product
     * @throws StoreException if the product cannot be found
     */
    Product findById(Long id) throws StoreException;

    /**
     * Finds all products.
     *
     * @return a list of all products
     */
    List<Product> findAll();

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     */
    void deleteById(Long id);
}
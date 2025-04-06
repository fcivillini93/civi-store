package com.fcivillini.store_repository.repository;

    import com.fcivillini.store_repository.dao.ProductDao;

    import java.util.List;
    import java.util.Optional;

    /**
     * Repository interface for managing products.
     */
    public interface ProductRepository {

        /**
         * Finds a product by its ID.
         *
         * @param id the ID of the product to find
         * @return an Optional containing the found product, or empty if not found
         */
        Optional<ProductDao> findById(Long id);

        /**
         * Saves a new product.
         *
         * @param product the product to save
         * @return the saved product
         */
        ProductDao save(ProductDao product);

        /**
         * Saves a list of products.
         *
         * @param productList the list of products to save
         * @return the list of saved products
         */
        List<ProductDao> saveAll(List<ProductDao> productList);

        /**
         * Deletes a product by its ID.
         *
         * @param id the ID of the product to delete
         */
        void deleteById(Long id);

        /**
         * Finds all products.
         *
         * @return a list of all products
         */
        List<ProductDao> findAll();
    }
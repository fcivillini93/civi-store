package com.fcivillini.store_rdbms.repository.jpa;

import com.fcivillini.store_rdbms.entity.ProductRdbms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<ProductRdbms, Long> {

    @Query("SELECT p.stock FROM ProductRdbms p WHERE p.id = :productId")
    int getStockByProductId(Long productId);

    @Modifying
    @Transactional
    @Query("UPDATE ProductRdbms p SET p.stock = p.stock - :quantity WHERE p.id = :productId AND p.stock >= :quantity")
    int decreaseStock(Long productId, int quantity);

    @Modifying
    @Transactional
    @Query("UPDATE ProductRdbms p SET p.stock = p.stock + :quantity WHERE p.id = :productId")
    void increaseStock(Long productId, int quantity);
}

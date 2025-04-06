package com.fcivillini.store_rdbms.repository.jpa;

import com.fcivillini.store_rdbms.entity.OrderRdbms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderRdbms, Long> {

    @Query("""
                SELECT o FROM OrderRdbms o
                WHERE (:startDate IS NULL OR o.orderDate >= :startDate AND o.orderDate < :endDate)
                AND (:userId IS NULL OR o.user.id = :userId)
                AND (:description IS NULL OR LOWER(o.description) LIKE LOWER(CONCAT('%', :description, '%')))
            """)
    List<OrderRdbms> findOrders(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("userId") Long userId,
            @Param("description") String description
    );
}
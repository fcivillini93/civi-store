package com.fcivillini.store_rdbms.repository.jpa;

import com.fcivillini.store_rdbms.entity.OrderRdbms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<OrderRdbms, Long> {

//    @Query("SELECT o FROM OrderRdbms o WHERE " +
//            "(:date IS NULL OR o.date = :date) AND " +
//            "(:name IS NULL OR LOWER(o.customer.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
//            "(:description IS NULL OR LOWER(o.description) LIKE LOWER(CONCAT('%', :description, '%')))")
//    List<OrderRdbms> findOrders(@Param("date") LocalDate date,
//                                @Param("name") String name,
//                                @Param("description") String description);
}
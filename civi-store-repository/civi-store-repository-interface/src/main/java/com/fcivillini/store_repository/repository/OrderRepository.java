package com.fcivillini.store_repository.repository;

import com.fcivillini.store_repository.dao.OrderDao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Optional<OrderDao> findById(Long id);

    List<OrderDao> findOrders(LocalDate date, String name, String description);

    OrderDao save(OrderDao order);

    void deleteById(Long id);

}

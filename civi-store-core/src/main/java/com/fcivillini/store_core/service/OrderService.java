package com.fcivillini.store_core.service;

import com.fcivillini.store_core.model.Order;
import com.fcivillini.store_interface.exc.StoreException;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Order save(Order order) throws StoreException;

    Order update(Order order) throws StoreException;

    Order findById(Long id) throws StoreException;

    List<Order> findOrders(LocalDate date, String name, String description) throws StoreException;

    void deleteById(Long id) throws StoreException;
}


package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_rdbms.mapper.OrderMapperRdbms;
import com.fcivillini.store_rdbms.repository.jpa.OrderRepositoryJpa;
import com.fcivillini.store_repository.dao.OrderDao;
import com.fcivillini.store_repository.repository.OrderRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Setter
@Accessors(chain = true)
public class OrderRepositoryRdbms implements OrderRepository {

    @Autowired
    private OrderMapperRdbms orderMapper;

    @Autowired
    private OrderRepositoryJpa orderRepositoryJpa;


    @Override
    public Optional<OrderDao> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<OrderDao> findOrders(LocalDate date, String name, String description) {
        return List.of();
    }

    @Override
    public OrderDao save(OrderDao order) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

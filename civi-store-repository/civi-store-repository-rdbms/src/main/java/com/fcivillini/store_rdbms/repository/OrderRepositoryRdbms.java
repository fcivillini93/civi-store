package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_interface.exc.StoreException;
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
import java.time.LocalDateTime;
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
    private OrderRepositoryJpa repositoryJpa;

    @Override
    public Optional<OrderDao> findById(Long id) {
        return repositoryJpa.findById(id).map(p -> orderMapper.fromRdbms(p));
    }

    @Override
    public List<OrderDao> findOrders(LocalDate date, Long userId, String description) throws StoreException {
        LocalDateTime startDate = date == null ? null : date.atStartOfDay();
        LocalDateTime endDate = date == null ? null : date.plusDays(1).atStartOfDay();
        return repositoryJpa.findOrders(startDate, endDate, userId, description).stream().map(o -> orderMapper.fromRdbms(o)).toList();
    }

    @Override
    public OrderDao save(OrderDao order) {
        return orderMapper.fromRdbms(repositoryJpa.save(orderMapper.toRdbms(order)));
    }

    @Override
    public void deleteById(Long id) {
        repositoryJpa.deleteById(id);
    }
}

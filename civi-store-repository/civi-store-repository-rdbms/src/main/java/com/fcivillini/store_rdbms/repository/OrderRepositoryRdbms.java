package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_rdbms.repository.jpa.OrderRepositoryJpa;
import com.fcivillini.store_repository.repository.OrderRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Setter
@Accessors(chain = true)
public class OrderRepositoryRdbms implements OrderRepository {

    @Autowired
    private OrderRepositoryJpa orderRepositoryJpa;


}

package com.fcivillini.store_repository.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class OrderDao {

    private Long id;

    private UserDao user;

    private String description;

    private LocalDateTime orderDate;

    private OrderStatusDao status;

    private List<OrderItemDao> items;
}


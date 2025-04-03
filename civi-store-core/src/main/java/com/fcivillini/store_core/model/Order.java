package com.fcivillini.store_core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class Order {

    private Long id;
    private User user;
    private String description;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderItem> items;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}


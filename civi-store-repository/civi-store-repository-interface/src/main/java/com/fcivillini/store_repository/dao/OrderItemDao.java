package com.fcivillini.store_repository.dao;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderItemDao {

    private Long id;

    private OrderDao order;

    private ProductDao product;

    private int quantity;
}


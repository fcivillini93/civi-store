package com.fcivillini.store_repository.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class OrderItemDao {

    private Long id;

    private ProductDao product;

    private Integer quantity;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}


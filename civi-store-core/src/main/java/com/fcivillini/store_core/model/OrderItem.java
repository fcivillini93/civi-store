package com.fcivillini.store_core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class OrderItem {

    private Long id;

    private Product product;

    private Integer quantity;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}


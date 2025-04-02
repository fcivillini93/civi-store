package com.fcivillini.store_repository.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ProductDao {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private int stockQuantity;
}


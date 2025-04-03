package com.fcivillini.store_core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class User {

    private Long id;
    private String email;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}


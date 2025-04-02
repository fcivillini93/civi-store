package com.fcivillini.store_repository.dao;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDao {

    private Long id;
    private String email;
    private String name;

}


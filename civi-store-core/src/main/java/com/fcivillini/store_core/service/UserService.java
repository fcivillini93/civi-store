package com.fcivillini.store_core.service;

import com.fcivillini.store_core.model.User;
import com.fcivillini.store_interface.exc.StoreException;

import java.util.List;

public interface UserService {
    User findById(Long id) throws StoreException;

    List<User> findAll();

    User save(User user);

    void deleteById(Long id);
}

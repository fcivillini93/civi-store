package com.fcivillini.store_repository.repository;

import com.fcivillini.store_repository.dao.UserDao;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<UserDao> findById(Long id);

    UserDao save(UserDao order);

    void deleteById(Long id);

    List<UserDao> findAll();
}

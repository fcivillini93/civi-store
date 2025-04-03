package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_rdbms.mapper.UserMapperRdbms;
import com.fcivillini.store_rdbms.repository.jpa.UserRepositoryJpa;
import com.fcivillini.store_repository.dao.UserDao;
import com.fcivillini.store_repository.repository.UserRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@Setter
@Accessors(chain = true)
public class UserRepositoryRdbms implements UserRepository {

    @Autowired
    private UserMapperRdbms userMapper;

    @Autowired
    private UserRepositoryJpa repositoryJpa;

    @Override
    public Optional<UserDao> findById(Long id) {
        return repositoryJpa.findById(id).map(p -> userMapper.fromRdbms(p));
    }

    @Override
    public UserDao save(UserDao product) {
        return userMapper.fromRdbms(repositoryJpa.save(userMapper.toRdbms(product)));
    }

    @Override
    public void deleteById(Long id) {
        repositoryJpa.deleteById(id);
    }

    @Override
    public List<UserDao> findAll() {
        return repositoryJpa.findAll().stream().map(userMapper::fromRdbms).collect(Collectors.toList());
    }
}

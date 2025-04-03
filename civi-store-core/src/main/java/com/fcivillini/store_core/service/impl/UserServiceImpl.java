package com.fcivillini.store_core.service.impl;

import com.fcivillini.store_core.mapper.UserMapper;
import com.fcivillini.store_core.model.User;
import com.fcivillini.store_core.service.UserService;
import com.fcivillini.store_interface.exc.StoreException;
import com.fcivillini.store_repository.dao.UserDao;
import com.fcivillini.store_repository.repository.UserRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Setter
@Service
@Accessors(chain = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(final Long id) throws StoreException {
        log.info("Start to find user by id [{}]", id);
        User result = userRepository.findById(id).map(userMapper::fromDao)
                .orElseThrow(() -> new StoreException(String.format("User [%s] not Found", id), HttpStatus.NOT_FOUND));
        log.info("End to find user by id [{}]", id);
        return result;
    }

    @Override
    public List<User> findAll() {
        log.info("Start to find all users");
        List<User> result = userRepository.findAll()
                .stream()
                .map(userMapper::fromDao)
                .collect(Collectors.toList());
        log.info("End to find all users. Found [{}] users", result.size());
        return result;
    }

    @Override
    public User save(final User user) {
        log.info("Start to save user [{}]", user.getName());
        UserDao savedUserDao = userRepository.save(userMapper.toDao(user));
        log.info("End to save user [{}]", user.getName());
        return userMapper.fromDao(savedUserDao);
    }

    @Override
    public void deleteById(final Long id) {
        log.info("Start to delete user with id [{}]", id);
        userRepository.deleteById(id);
        log.info("End to delete user with id [{}]", id);
    }
}

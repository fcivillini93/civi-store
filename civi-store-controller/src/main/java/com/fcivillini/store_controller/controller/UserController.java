package com.fcivillini.store_controller.controller;


import com.fcivillini.store_core.mapper.UserMapper;
import com.fcivillini.store_core.service.UserService;
import com.fcivillini.store_interface.dto.UserDto;
import com.fcivillini.store_interface.exc.StoreException;
import com.fcivillini.store_interface.provider.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserController implements UserProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) throws StoreException {
        return new ResponseEntity<>(userMapper.toDto(userService.findById(id)), OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(
                userService.findAll()
                        .stream()
                        .map(userMapper::toDto)
                        .collect(Collectors.toList()), OK);
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        return new ResponseEntity<>(userMapper.toDto(userService.save(userMapper.fromDto(userDto))), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDto> editUser(UserDto userDto) {
        return new ResponseEntity<>(userMapper.toDto(userService.save(userMapper.fromDto(userDto))), OK);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

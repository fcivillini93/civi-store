package com.fcivillini.store_core.service;

import com.fcivillini.store_core.mapper.UserMapper;
import com.fcivillini.store_core.model.User;
import com.fcivillini.store_core.service.impl.UserServiceImpl;
import com.fcivillini.store_interface.exc.StoreException;
import com.fcivillini.store_repository.dao.UserDao;
import com.fcivillini.store_repository.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        Mockito.clearInvocations(userRepository, userMapper);
    }

    @Test
    void testFindById() throws StoreException {
        Long userId = 1L;
        UserDao userDao = new UserDao();
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userDao));
        when(userMapper.fromDao(userDao)).thenReturn(user);

        User result = userService.findById(userId);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(userId);
        verify(userMapper, times(1)).fromDao(userDao);
    }

    @Test
    void testFindById_UserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        StoreException exception = assertThrows(StoreException.class, () -> userService.findById(userId));

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        verify(userRepository, times(1)).findById(userId);
        verify(userMapper, never()).fromDao(any());
    }

    @Test
    void testFindAll() {
        UserDao userDao1 = new UserDao();
        User user1 = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(userDao1));
        when(userMapper.fromDao(userDao1)).thenReturn(user1);

        List<User> result = userService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(user1));
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).fromDao(userDao1);
    }

    @Test
    void testSave() {
        User user = new User();
        UserDao userDao = new UserDao();
        UserDao savedUserDao = new UserDao();
        User savedUser = new User();
        when(userMapper.toDao(user)).thenReturn(userDao);
        when(userRepository.save(userDao)).thenReturn(savedUserDao);
        when(userMapper.fromDao(savedUserDao)).thenReturn(savedUser);

        User result = userService.save(user);

        assertNotNull(result);
        assertEquals(savedUser, result);
        verify(userMapper, times(1)).toDao(user);
        verify(userRepository, times(1)).save(userDao);
        verify(userMapper, times(1)).fromDao(savedUserDao);
    }

    @Test
    void testDeleteById() {
        Long userId = 1L;

        userService.deleteById(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
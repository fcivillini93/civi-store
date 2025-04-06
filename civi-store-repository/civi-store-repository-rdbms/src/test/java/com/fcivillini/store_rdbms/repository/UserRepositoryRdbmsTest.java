package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_repository.dao.UserDao;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryRdbmsTest extends AbstractRepositoryRdbmsTest {

    @Test
    void testSaveAndFindById() {
        UserDao user = new UserDao()
                .setName("user")
                .setEmail("email@example.com");

        UserDao savedUser = userRepository.save(user);
        Optional<UserDao> foundUser = userRepository.findById(savedUser.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(savedUser, foundUser.get());
    }

    @Test
    void testFindAll() {
        UserDao u1 = userRepository.save(new UserDao().setName("Alice").setEmail("alice@example.com"));
        UserDao u2 = userRepository.save(new UserDao().setName("Bob").setEmail("bob@example.com"));

        List<UserDao> allUsers = userRepository.findAll();

        assertTrue(allUsers.contains(u1));
        assertTrue(allUsers.contains(u2));
    }

    @Test
    void testDeleteById() {
        UserDao user = userRepository.save(new UserDao()
                .setName("ToDelete")
                .setEmail("delete@example.com"));

        Long userId = user.getId();
        userRepository.deleteById(userId);

        Optional<UserDao> deletedUser = userRepository.findById(userId);
        assertTrue(deletedUser.isEmpty());
    }
}
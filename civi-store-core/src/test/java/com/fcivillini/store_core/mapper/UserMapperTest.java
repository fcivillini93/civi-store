package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.configuration.CoreMapperConfiguration;
import com.fcivillini.store_core.model.User;
import com.fcivillini.store_interface.dto.UserDto;
import com.fcivillini.store_repository.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = CoreMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void test_fromDao() {
        UserDao userDao = new UserDao()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        assertEquals(new User()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)), mapper.fromDao(userDao));

    }

    @Test
    void test_fromDto() {
        UserDto userDto = new UserDto()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        assertEquals(new User()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)), mapper.fromDto(userDto));

    }

    @Test
    void test_toDao() {

        User user = new User()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));
        assertEquals(new UserDao()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)), mapper.toDao(user));

    }

    @Test
    void test_toDto() {
        User user = new User()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));
        assertEquals(new UserDto()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10)), mapper.toDto(user));

    }


}
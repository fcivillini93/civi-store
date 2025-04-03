package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.configuration.RdbmsMapperConfiguration;
import com.fcivillini.store_rdbms.entity.UserRdbms;
import com.fcivillini.store_repository.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = RdbmsMapperConfiguration.class)
@ExtendWith(SpringExtension.class)
class UserMapperRdbmsTest {

    @Autowired
    private UserMapperRdbms mapper;

    @Test
    void test_toRdbms() {
        UserDao userDao = new UserDao()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        UserRdbms userRdbms = new UserRdbms()
                .setId(1L)
                .setName("user")
                .setEmail("email@email.com")
                .setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10));

        UserRdbms result = mapper.toRdbms(userDao);
        assertEquals(userRdbms.getId(), result.getId());
        assertEquals(userRdbms.getName(), result.getName());
        assertEquals(userRdbms.getEmail(), result.getEmail());
        assertEquals(userRdbms.getCreatedDate(), result.getCreatedDate());
        assertEquals(userRdbms.getUpdatedDate(), result.getUpdatedDate());

    }

    @Test
    void test_fromRdbms() {
        UserRdbms userRdbms = new UserRdbms()
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
                        .setUpdatedDate(LocalDateTime.of(2020, 1, 1, 0, 10))
                , mapper.fromRdbms(userRdbms));
    }
}
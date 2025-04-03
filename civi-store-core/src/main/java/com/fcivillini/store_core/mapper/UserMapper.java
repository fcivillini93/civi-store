package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.model.User;
import com.fcivillini.store_interface.dto.UserDto;
import com.fcivillini.store_repository.dao.UserDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDao(UserDao user);
    UserDao toDao(User user);

    User fromDto(UserDto user);
    UserDto toDto(User user);
}

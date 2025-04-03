package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.entity.UserRdbms;
import com.fcivillini.store_repository.dao.UserDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperRdbms {

    UserRdbms toRdbms(UserDao user);

    UserDao fromRdbms(UserRdbms user);
}

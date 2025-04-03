package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.model.OrderStatusRdbms;
import com.fcivillini.store_repository.dao.OrderStatusDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStatusMapperRdbms {

    OrderStatusRdbms toRdbms(OrderStatusDao status);

    OrderStatusDao fromRdbms(OrderStatusRdbms status);
}

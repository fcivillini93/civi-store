package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.entity.OrderItemRdbms;
import com.fcivillini.store_repository.dao.OrderItemDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapperRdbms.class})
public interface OrderItemMapperRdbms {

    OrderItemRdbms toRdbms(OrderItemDao item);

    OrderItemDao fromRdbms(OrderItemRdbms item);
}

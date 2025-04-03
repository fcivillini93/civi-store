package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.entity.OrderRdbms;
import com.fcivillini.store_repository.dao.OrderDao;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {OrderItemMapperRdbms.class, ProductMapperRdbms.class})
public interface OrderMapperRdbms {

    OrderRdbms toRdbms(OrderDao order);

    OrderDao fromRdbms(OrderRdbms order);

    @AfterMapping
    default void fixOrderItems(@MappingTarget OrderRdbms order) {
        if (order.getItems() != null) {
            order.getItems().forEach(item -> item.setOrder(order));
        }
    }
}

package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.model.OrderStatus;
import com.fcivillini.store_interface.dto.OrderStatusDto;
import com.fcivillini.store_repository.dao.OrderStatusDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {

    OrderStatus fromDao(OrderStatusDao status);
    OrderStatusDao toDao(OrderStatus status);

    OrderStatus fromDto(OrderStatusDto status);
    OrderStatusDto toDto(OrderStatus status);
}

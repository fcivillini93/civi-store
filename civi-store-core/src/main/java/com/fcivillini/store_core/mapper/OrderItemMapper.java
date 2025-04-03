package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.model.OrderItem;
import com.fcivillini.store_interface.dto.OrderItemDto;
import com.fcivillini.store_repository.dao.OrderItemDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderItemMapper {

    OrderItem fromDao(OrderItemDao item);
    OrderItemDao toDao(OrderItem item);

    OrderItem fromDto(OrderItemDto item);
    OrderItemDto toDto(OrderItem item);
}

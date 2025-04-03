package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.model.Order;
import com.fcivillini.store_interface.dto.OrderDto;
import com.fcivillini.store_repository.dao.OrderDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, ProductMapper.class})
public interface OrderMapper {

    Order fromDao(OrderDao order);
    OrderDao toDao(Order order);

    Order fromDto(OrderDto order);
    OrderDto toDto(Order order);
}

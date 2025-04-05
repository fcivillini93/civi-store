package com.fcivillini.store_controller.controller;

import com.fcivillini.store_core.mapper.OrderMapper;
import com.fcivillini.store_core.service.OrderService;
import com.fcivillini.store_interface.dto.OrderDto;
import com.fcivillini.store_interface.exc.StoreException;
import com.fcivillini.store_interface.provider.OrderProvider;
import jakarta.validation.Valid;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Accessors(chain = true)
@RestController
public class OrderController implements OrderProvider {

    private OrderService orderService;
    private OrderMapper orderMapper;

    @Override
    public ResponseEntity<OrderDto> save(@Valid OrderDto order) throws StoreException {
        return new ResponseEntity<>(orderMapper.toDto(
                orderService.save(orderMapper.fromDto(order))
        ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderDto> update(@Valid OrderDto order) throws StoreException {
        return new ResponseEntity<>(orderMapper.toDto(orderService.update(orderMapper.fromDto(order))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderDto> findById(Long id) throws StoreException {
        return new ResponseEntity<>(orderMapper.toDto(orderService.findById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDto>> findOrders(LocalDate date, Long userId, String description) throws StoreException {
        return new ResponseEntity<>(
                orderService.findOrders(date, userId, description).stream()
                        .map(orderMapper::toDto)
                        .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) throws StoreException {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

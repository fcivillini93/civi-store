package com.fcivillini.store_interface.provider;

import com.fcivillini.store_interface.dto.OrderDto;
import com.fcivillini.store_interface.exc.StoreException;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/orders")
public interface OrderProvider {

    @PostMapping
    public ResponseEntity<OrderDto> save(@RequestBody @Valid OrderDto order) throws StoreException;

    @PutMapping
    public ResponseEntity<OrderDto> update(@RequestBody @Valid OrderDto order) throws StoreException;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) throws StoreException;

    @GetMapping
    public ResponseEntity<List<OrderDto>> findOrders(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) throws StoreException;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws StoreException;
}

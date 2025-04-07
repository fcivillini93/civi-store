package com.fcivillini.store_interface.provider;

import com.fcivillini.store_interface.dto.OrderDto;
import com.fcivillini.store_interface.exc.StoreException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/v1/orders")
@Tag(name = "Order", description = "API for managing orders")
public interface OrderProvider {

    @Operation(summary = "Create a new order", description = "Creates a new order and returns the created order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<OrderDto> save(@RequestBody @Valid OrderDto order) throws StoreException;

    @Operation(summary = "Update an existing order", description = "Updates an existing order and returns the updated order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping
    public ResponseEntity<OrderDto> update(@RequestBody @Valid OrderDto order) throws StoreException;

    @Operation(summary = "Find an order by ID", description = "Finds an order by its ID and returns the order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id) throws StoreException;

    @Operation(summary = "Find orders", description = "Finds orders based on the provided criteria and returns the list of orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders found successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<OrderDto>> findOrders(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String description) throws StoreException;

    @Operation(summary = "Delete an order by ID", description = "Deletes an order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws StoreException;
}

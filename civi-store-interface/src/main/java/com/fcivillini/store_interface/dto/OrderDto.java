package com.fcivillini.store_interface.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Schema(description = "Data Transfer Object for Order")
public class OrderDto {

    @Schema(description = "Unique identifier of the order", example = "1")
    private Long id;
    @Schema(description = "User associated with the order")
    private UserDto user;
    @Schema(description = "Description of the order", example = "description")
    private String description;
    @Schema(description = "Date and time when the order was placed", example = "2020-01-01T00:20:00")
    private LocalDateTime orderDate;
    @Schema(description = "Status of the order")
    private OrderStatusDto status;
    @Schema(description = "List of items in the order")
    private List<OrderItemDto> items;
    @Schema(description = "Date and time when the order was created", example = "2020-01-01T00:00:00")
    private LocalDateTime createdDate;
    @Schema(description = "Date and time when the order was last updated", example = "2020-01-01T00:10:00")
    private LocalDateTime updatedDate;
}


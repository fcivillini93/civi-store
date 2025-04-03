package com.fcivillini.store_interface.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Schema(description = "Data Transfer Object for Order Item")
public class OrderItemDto {

    @Schema(description = "Unique identifier of the order item", example = "1")
    private Long id;

    @Schema(description = "Product associated with the order item")
    private ProductDto product;

    @Schema(description = "Quantity of the product ordered", example = "2")
    private Integer quantity;

    @Schema(description = "Date and time when the order item was created", example = "2023-01-01T00:00:00")
    private LocalDateTime createdDate;

    @Schema(description = "Date and time when the order item was last updated", example = "2023-01-10T00:00:00")
    private LocalDateTime updatedDate;
}


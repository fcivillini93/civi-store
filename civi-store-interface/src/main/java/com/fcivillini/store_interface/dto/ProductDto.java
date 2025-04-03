package com.fcivillini.store_interface.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Schema(description = "Data Transfer Object for Product")
public class ProductDto {

    @Schema(description = "Unique identifier of the product", example = "1")
    private Long id;

    @Schema(description = "Name of the product", example = "Laptop")
    private String name;

    @Schema(description = "Description of the product", example = "A high-end gaming laptop")
    private String description;

    @Schema(description = "Price of the product", example = "999.99")
    private BigDecimal price;

    @Schema(description = "Stock quantity of the product", example = "50")
    private Integer stock;

    @Schema(description = "Date when the product was created", example = "2023-01-01T00:00:00")
    private LocalDateTime createdDate;

    @Schema(description = "Date when the product was last updated", example = "2023-01-10T00:00:00")
    private LocalDateTime updatedDate;
}


package com.fcivillini.store_interface.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Status of the order")
public enum OrderStatusDto {
    @Schema(description = "Order is pending")
    PENDING,
    @Schema(description = "Order is confirmed")
    CONFIRMED,
    @Schema(description = "Order is cancelled")
    CANCELLED
}


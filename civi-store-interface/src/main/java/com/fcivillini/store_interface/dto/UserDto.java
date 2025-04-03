package com.fcivillini.store_interface.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Schema(description = "Data Transfer Object for User")
public class UserDto {

    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "Email of the user", example = "user@example.com")
    @NotNull(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be null")
    private String email;

    @Schema(description = "Name of the user", example = "John Doe")
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be null")
    private String name;

    @Schema(description = "Date and time when the user was created", example = "2023-01-01T00:00:00")
    private LocalDateTime createdDate;

    @Schema(description = "Date and time when the user was last updated", example = "2023-01-10T00:00:00")
    private LocalDateTime updatedDate;
}


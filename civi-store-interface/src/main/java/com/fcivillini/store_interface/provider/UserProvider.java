package com.fcivillini.store_interface.provider;

import com.fcivillini.store_interface.dto.UserDto;
import com.fcivillini.store_interface.exc.StoreException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserProvider {

    @Operation(summary = "Get a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws StoreException;

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))})})
    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers();

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))})})
    @PostMapping
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto);

    @Operation(summary = "Edit an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @PutMapping
    ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto);

    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}

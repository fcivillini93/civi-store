package com.fcivillini.store_interface.provider;


import com.fcivillini.store_interface.dto.ProductDto;
import com.fcivillini.store_interface.exc.StoreException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@Tag(name = "Product", description = "API for managing products")
public interface ProductProvider {

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))})})
    @PostMapping
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto dto);

    @Operation(summary = "Update an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @PutMapping()
    ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto dto);

    @Operation(summary = "Get a product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws StoreException;

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all products",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))})})
    @GetMapping
    ResponseEntity<List<ProductDto>> getAllProducts();

    @Operation(summary = "Delete a product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable Long id);
}

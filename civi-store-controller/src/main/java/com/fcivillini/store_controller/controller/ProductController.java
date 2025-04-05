package com.fcivillini.store_controller.controller;


import com.fcivillini.store_core.mapper.ProductMapper;
import com.fcivillini.store_core.service.ProductService;
import com.fcivillini.store_interface.dto.ProductDto;
import com.fcivillini.store_interface.exc.StoreException;
import com.fcivillini.store_interface.provider.ProductProvider;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@Setter
@Accessors(chain = true)
@RestController
public class ProductController implements ProductProvider {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseEntity<ProductDto> createProduct(ProductDto dto) {
        return new ResponseEntity<>(productMapper.toDto(productService.save(productMapper.fromDto(dto))), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(ProductDto dto) {
        return new ResponseEntity<>(productMapper.toDto(productService.update(productMapper.fromDto(dto))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> getProduct(Long id) throws StoreException {
        return new ResponseEntity<>(productMapper.toDto(productService.findById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


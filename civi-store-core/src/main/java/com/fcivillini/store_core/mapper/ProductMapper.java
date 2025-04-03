package com.fcivillini.store_core.mapper;

import com.fcivillini.store_core.model.Product;
import com.fcivillini.store_interface.dto.ProductDto;
import com.fcivillini.store_repository.dao.ProductDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromDao(ProductDao product);
    ProductDao toDao(Product product);

    Product fromDto(ProductDto product);
    ProductDto toDto(Product product);
}

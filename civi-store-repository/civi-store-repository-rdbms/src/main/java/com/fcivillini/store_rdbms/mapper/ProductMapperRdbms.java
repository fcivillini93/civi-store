package com.fcivillini.store_rdbms.mapper;

import com.fcivillini.store_rdbms.entity.ProductRdbms;
import com.fcivillini.store_repository.dao.ProductDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperRdbms {

    ProductRdbms toRdbms(ProductDao product);

    ProductDao fromRdbms(ProductRdbms product);
}

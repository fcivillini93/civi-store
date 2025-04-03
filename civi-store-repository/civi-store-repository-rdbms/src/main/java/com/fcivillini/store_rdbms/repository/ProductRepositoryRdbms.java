package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_rdbms.mapper.ProductMapperRdbms;
import com.fcivillini.store_rdbms.repository.jpa.ProductRepositoryJpa;
import com.fcivillini.store_repository.dao.ProductDao;
import com.fcivillini.store_repository.repository.ProductRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@Setter
@Accessors(chain = true)
public class ProductRepositoryRdbms implements ProductRepository {

    @Autowired
    private ProductMapperRdbms productMapper;

    @Autowired
    private ProductRepositoryJpa repositoryJpa;

    @Override
    public Optional<ProductDao> findById(Long id) {
        return repositoryJpa.findById(id).map(p -> productMapper.fromRdbms(p));
    }

    @Override
    public ProductDao save(ProductDao product) {
        return productMapper.fromRdbms(repositoryJpa.save(productMapper.toRdbms(product)));
    }

    @Override
    public void deleteById(Long id) {
        repositoryJpa.deleteById(id);
    }
}

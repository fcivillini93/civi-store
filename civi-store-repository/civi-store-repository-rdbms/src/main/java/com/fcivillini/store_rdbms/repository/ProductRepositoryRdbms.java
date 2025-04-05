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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ProductDao> saveAll(List<ProductDao> productList) {
        return repositoryJpa.saveAll(productList.stream().map(p->productMapper.toRdbms(p)).toList()).stream()
                .map(p -> productMapper.fromRdbms(p))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repositoryJpa.deleteById(id);
    }

    @Override
    public List<ProductDao> findAll() {
        return repositoryJpa.findAll().stream().map(productMapper::fromRdbms).collect(Collectors.toList());
    }
}

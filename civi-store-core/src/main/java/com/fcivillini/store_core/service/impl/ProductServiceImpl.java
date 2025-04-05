package com.fcivillini.store_core.service.impl;

import com.fcivillini.store_core.mapper.ProductMapper;
import com.fcivillini.store_core.model.Product;
import com.fcivillini.store_core.service.ProductService;
import com.fcivillini.store_interface.exc.StoreException;
import com.fcivillini.store_repository.repository.ProductRepository;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Setter
@Accessors(chain = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        log.info("start to save product : [{}]", product.getName());
        Product result = productMapper.fromDao(productRepository.save(productMapper.toDao(product)));
        log.info("end  to save product : [{}]", product.getName());
        return result;
    }

    @Override
    public Product update(Product product) throws StoreException {
        log.info("start to update product with id: [{}]", product.getId());
        Product result = productRepository.findById(product.getId())
                .map(existing -> {
                    return productMapper.fromDao(productRepository.save(productMapper.toDao(product)));
                }).orElseThrow(() -> new StoreException(String.format("Product with id [%s] not found", product.getId()), HttpStatus.NOT_FOUND));
        log.info("end to update product with id: [{}]", product.getId());
        return result;
    }

    @Override
    public Product findById(Long id) throws StoreException {
        log.info("start fo find product by id [{}]", id);
        Product product = productRepository.findById(id).map(productMapper::fromDao)
                .orElseThrow(() -> new StoreException(String.format("Product with id [%s] not found", id), HttpStatus.NOT_FOUND));

        log.info("end fo find product by id [{}]", id);
        return product;
    }

    @Override
    public List<Product> findAll() {
        log.info("start fo find all products");
        List<Product> result = productRepository.findAll()
                .stream()
                .map(productMapper::fromDao)
                .collect(Collectors.toList());
        log.info("end fo find all products. Found [{}] products", result.size());
        return result;
    }

    @Override
    public void deleteById(Long id) {
        log.info("start fo delete product by id [{}]", id);
        productRepository.deleteById(id);
        log.info("end fo delete product by id [{}]", id);
    }
}

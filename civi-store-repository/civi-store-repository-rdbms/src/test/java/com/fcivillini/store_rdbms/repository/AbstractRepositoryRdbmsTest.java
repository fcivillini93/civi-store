package com.fcivillini.store_rdbms.repository;

import com.fcivillini.store_rdbms.configuration.RdbmsConfiguration;
import com.fcivillini.store_repository.repository.ProductRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RdbmsConfiguration.class)
abstract class AbstractRepositoryRdbmsTest {

    @Autowired
    protected UserRepositoryRdbms userRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected OrderRepositoryRdbms orderRepository;

}
package com.fcivillini.store_rdbms.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories("com.fcivillini.store_rdbms.repository.jpa")
@EntityScan("com.fcivillini.store_rdbms.entity")
@ComponentScan(basePackages = "com.fcivillini.store_rdbms.repository")
public class RdbmsRepositoryConfiguration {
}

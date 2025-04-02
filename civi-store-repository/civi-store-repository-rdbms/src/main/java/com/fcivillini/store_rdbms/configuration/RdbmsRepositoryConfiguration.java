package com.fcivillini.store_rdbms.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.fcivillini.store_rdbms.repository")
public class RdbmsRepositoryConfiguration {
}

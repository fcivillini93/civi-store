package com.fcivillini.store_rdbms.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RdbmsMapperConfiguration.class,
        RdbmsRepositoryConfiguration.class
})
public class RdbmsConfiguration {
}

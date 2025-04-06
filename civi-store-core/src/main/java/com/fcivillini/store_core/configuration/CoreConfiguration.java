package com.fcivillini.store_core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RedissonConfiguration.class,
        CoreMapperConfiguration.class,
        CoreServiceConfiguration.class
})
public class CoreConfiguration {
}

package com.fcivillini.store_core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConfiguration;

@Configuration
@Import({
        RedisConfiguration.class,
        CoreMapperConfiguration.class,
        CoreServiceConfiguration.class
})
public class CoreConfiguration {
}

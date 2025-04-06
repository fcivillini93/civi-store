package com.fcivillini.store_core.configuration;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfiguration {


    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(@Value("${spring.data.redis.host}") final String redisHost,
                                         @Value("${spring.data.redis.port}") final int redisPort,
                                         @Value("${spring.data.redis.password:}") final String redisPassword) {
        Config config = new Config();
        SingleServerConfig singleServer = config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);

        if (StringUtils.isNoneBlank(redisPassword)) {
            singleServer.setPassword(redisPassword);
        }

        return Redisson.create(config);
    }
}

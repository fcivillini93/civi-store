package com.fcivillini.store_core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        CoreMapperConfiguration.class
})
public class CoreConfiguration {
}

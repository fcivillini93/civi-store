package com.fcivillini.store_controller.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.fcivillini.store_controller.controller",
        "com.fcivillini.store_controller.service"
})
public class ControllerConfiguration {
}

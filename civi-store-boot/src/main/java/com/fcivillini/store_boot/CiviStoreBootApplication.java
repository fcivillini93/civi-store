package com.fcivillini.store_boot;

import com.fcivillini.store_controller.configuration.ControllerConfiguration;
import com.fcivillini.store_core.configuration.CoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
        CoreConfiguration.class,
        ControllerConfiguration.class
})
@SpringBootApplication
public class CiviStoreBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CiviStoreBootApplication.class, args);
    }

}


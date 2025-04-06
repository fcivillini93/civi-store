package com.fcivillini.store_boot;

import com.fcivillini.store_controller.configuration.ControllerConfiguration;
import com.fcivillini.store_core.configuration.CoreConfiguration;
import com.fcivillini.store_rdbms.configuration.RdbmsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
        RdbmsConfiguration.class,
        CoreConfiguration.class,
        ControllerConfiguration.class
})
@SpringBootApplication()
public class CiviStoreBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CiviStoreBootApplication.class, args);
    }

}


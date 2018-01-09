package com.hx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 *
 */
@SpringBootApplication
@ImportResource(locations = { "classpath:jdbc-sharding-cfg.xml" })
public class Sharding2Application {
    public static void main(String[] args) {
        SpringApplication.run(Sharding2Application.class, args);

    }
}

package com.etour.banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main Spring Boot application class for the InsertBanner service.
 * This class is responsible for bootstrapping and launching the Spring Boot application.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.etour.banner"}) // Ensure all components are scanned
public class InsertBannerApplication {

    /**
     * The main method that serves as the entry point for the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(InsertBannerApplication.class, args);
    }

}
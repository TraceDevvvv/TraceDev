package com.yourcompany.banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main entry point for the Banner Management System Spring Boot application.
 * This class bootstraps the Spring application context.
 */
@SpringBootApplication // Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan.
@EnableJpaRepositories(basePackages = "com.yourcompany.banner.repository") // Explicitly enables JPA repositories.
public class BannerManagementApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(BannerManagementApplication.class, args);
    }

}
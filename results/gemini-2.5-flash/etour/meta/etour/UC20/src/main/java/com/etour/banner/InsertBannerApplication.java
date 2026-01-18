package com.etour.banner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main Spring Boot application class for the InsertBanner service.
 * This class serves as the entry point for the application,
 * enabling auto-configuration, component scanning, and JPA repositories.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.etour.banner.repository") // Explicitly enable JPA repositories
public class InsertBannerApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(InsertBannerApplication.class, args);
    }

}
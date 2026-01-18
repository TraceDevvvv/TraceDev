package com.example.insertdelayadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main entry point for the InsertDelayAdmin Spring Boot application.
 * This class bootstraps the application, enabling auto-configuration,
 * component scanning, and JPA repositories.
 */
@SpringBootApplication(scanBasePackages = "com.example.insertdelayadmin")
@EnableJpaRepositories(basePackages = "com.example.insertdelayadmin.repository")
public class InsertDelayAdminApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(InsertDelayAdminApplication.class, args);
    }

}
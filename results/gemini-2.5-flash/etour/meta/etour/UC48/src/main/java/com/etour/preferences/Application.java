package com.etour.preferences;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main entry point for the Spring Boot application.
 * This class initializes and runs the application.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.etour.preferences.repository")
public class Application {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
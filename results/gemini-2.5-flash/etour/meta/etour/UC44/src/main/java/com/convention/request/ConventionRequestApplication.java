package com.convention.request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Convention Request system.
 * This class serves as the entry point for the application.
 */
@SpringBootApplication
public class ConventionRequestApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ConventionRequestApplication.class, args);
    }

}
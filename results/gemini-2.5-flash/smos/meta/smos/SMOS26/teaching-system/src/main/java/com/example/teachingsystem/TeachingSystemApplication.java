package com.example.teachingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Teaching System.
 * This class serves as the entry point for the application.
 */
@SpringBootApplication
public class TeachingSystemApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Run the Spring Boot application. This method bootstraps the application,
        // creates an ApplicationContext, and registers all beans.
        SpringApplication.run(TeachingSystemApplication.class, args);
    }

}
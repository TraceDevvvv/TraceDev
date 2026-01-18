package com.atastaff.absencesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main Spring Boot application class for the InsertAbsencesSystem.
 * This class serves as the entry point for the application.
 * It uses @SpringBootApplication, which is a convenience annotation that adds:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings,
 *   other beans, and various property settings.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and serv
 *   in the 'com.atastaff.absencesystem' package, allowing it to find controllers, serv, and repositories.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.atastaff.absencesystem") // Explicitly define base package for component scanning
public class InsertAbsencesSystemApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(InsertAbsencesSystemApplication.class, args);
    }

}
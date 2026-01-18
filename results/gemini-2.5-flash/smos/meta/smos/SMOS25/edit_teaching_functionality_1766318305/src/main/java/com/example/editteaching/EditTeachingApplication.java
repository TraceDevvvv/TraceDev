package com.example.editteaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Edit Teaching functionality.
 * This class serves as the entry point for the application, enabling
 * Spring Boot's auto-configuration and component scanning.
 */
@SpringBootApplication // This annotation combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class EditTeachingApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Runs the Spring Boot application. This method creates and refreshes
        // the Spring application context, and then runs any CommandLineRunner beans.
        SpringApplication.run(EditTeachingApplication.class, args);
    }

}
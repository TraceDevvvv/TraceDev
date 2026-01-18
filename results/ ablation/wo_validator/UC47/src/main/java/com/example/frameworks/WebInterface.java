package com.example.frameworks;

import java.util.List;
import java.util.Map;

/**
 * Frameworks & Drivers Layer: Simulating a web interface (e.g., a REST controller or Thymeleaf view).
 * This class would be part of the framework (e.g., Spring MVC controller).
 * For this example, we simulate its methods.
 */
public class WebInterface {
    public void renderForm(Map<String, String> data) {
        System.out.println("Rendering form with data: " + data);
    }

    public void displayConfirmation(Map<String, String> data) {
        System.out.println("Displaying confirmation dialog for data: " + data);
    }

    public void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    public void displayError(List<String> errors) {
        System.out.println("ERRORS: " + errors);
    }
}
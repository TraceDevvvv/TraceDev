package com.example.application.controllers;

import java.util.List;

/**
 * Controller for handling general error scenarios.
 */
public class ErroredController {
    public void handle(List<String> validationErrors) {
        // Implementation could include logging, alerting, or other error handling.
        // For now, we just log to console as per the sequence diagram.
        System.out.println("ErroredController: Validation errors - " + validationErrors);
    }
}
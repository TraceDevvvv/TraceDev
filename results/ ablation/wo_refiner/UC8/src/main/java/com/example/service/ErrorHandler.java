package com.example.service;

import java.util.List;

/**
 * Handles errors and validation errors.
 * REQ-009: Error handling use case
 */
public class ErrorHandler {
    /**
     * Handles validation errors.
     * @param errors list of error messages
     */
    public void handleValidationErrors(List<String> errors) {
        System.err.println("Validation errors occurred:");
        for (String error : errors) {
            System.err.println(" - " + error);
        }
        // In a real system, might log to a file or send alerts.
    }

    /**
     * Handles system errors.
     * @param exception the exception that occurred
     */
    public void handleSystemError(Exception exception) {
        System.err.println("System error: " + exception.getMessage());
        exception.printStackTrace();
    }
}
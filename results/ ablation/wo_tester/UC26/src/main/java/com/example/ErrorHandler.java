package com.example;

/**
 * Handles errors and logs them.
 */
public class ErrorHandler {
    public void handleValidationError(String errorMessage) {
        System.err.println("Validation Error: " + errorMessage);
    }

    public void logError(String error) {
        System.err.println("Logged Error: " + error);
    }
}
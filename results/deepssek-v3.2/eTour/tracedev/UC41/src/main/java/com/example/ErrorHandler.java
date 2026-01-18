package com.example;

import java.util.List;

/**
 * ErrorHandler class to handle validation and connection errors.
 */
public class ErrorHandler {
    
    /**
     * Handle validation errors.
     * @param errors List of error messages.
     */
    public void handleValidationErrors(List<String> errors) {
        System.out.println("Validation errors:");
        for (String error : errors) {
            System.out.println(" - " + error);
        }
    }
    
    /**
     * Handle connection error.
     */
    public void handleConnectionError() {
        System.out.println("Connection error: Unable to connect to external system.");
    }
}
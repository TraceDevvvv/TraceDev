package com.example.ui;

import java.util.List;

/**
 * Error handler for displaying error messages.
 */
public class ErrorHandler {
    
    public void handleError(String errorMessage) {
        // Implementation to handle error
        System.err.println("Error handled: " + errorMessage);
    }
    
    public void displayErrorDetails(List<String> errors) {
        // Implementation to display error details
        System.err.println("Displaying error details:");
        if (errors != null) {
            for (String error : errors) {
                System.err.println("  - " + error);
            }
        }
    }
    
    public void handleConnectionError() {
        // Implementation to handle connection error
        System.err.println("Handling connection error...");
    }
}
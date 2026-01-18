package com.example.controller;

/**
 * Utility class for handling errors and generating user-friendly messages.
 */
public class ErrorHandler {
    
    public void handleError(Exception error) {
        System.err.println("Error handled: " + error.getMessage());
        // Could log to a monitoring system, send alerts, etc.
    }

    public String getErrorMessage(Exception error) {
        return "System error: " + error.getMessage();
    }
}
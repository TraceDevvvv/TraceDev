package com.example.controller;

import java.util.List;

/**
 * Control class dedicated to error handling flows.
 */
public class ErroredUseCase {
    
    public void handleValidationError(List<String> errors) {
        System.err.println("Validation errors encountered:");
        for (String err : errors) {
            System.err.println(" - " + err);
        }
        // Could involve user notification, logging, etc.
    }

    public void handleSystemError(Exception error) {
        System.err.println("System error handled by ErroredUseCase: " + error.getMessage());
        // Additional recovery or notification logic.
    }
}
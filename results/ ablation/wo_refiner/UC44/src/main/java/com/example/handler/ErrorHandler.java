package com.example.handler;

import java.util.List;

/**
 * Handles errors in the system.
 * Implements requirement REQ-009: Activates "Errored" use case when validation fails.
 */
public class ErrorHandler {
    /**
     * Handle validation errors.
     */
    public void handleValidationErrors(List<String> errors) {
        System.err.println("=== VALIDATION ERRORS DETECTED ===");
        for (String error : errors) {
            System.err.println("Error: " + error);
        }
        System.err.println("=== 'Errored' use case activated ===");
        // In a real system, would log to monitoring system, send alerts, etc.
    }
}
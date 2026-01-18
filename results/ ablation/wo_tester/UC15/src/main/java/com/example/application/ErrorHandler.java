package com.example.application;

import java.util.List;

/**
 * Handles errors and generates user-friendly messages.
 */
public class ErrorHandler {
    /**
     * Handles validation errors.
     */
    public void handleValidationError(List<String> errors) {
        System.err.println("Validation errors: " + errors);
    }

    /**
     * Handles system-level exceptions.
     */
    public void handleSystemError(Exception exception) {
        System.err.println("System error: " + exception.getMessage());
    }

    /**
     * Generates a user-friendly error message.
     */
    public String generateUserFriendlyErrorMessage() {
        return "An error occurred. Please try again later or contact support.";
    }

    /**
     * Triggers an errored use case (e.g., logging, alerting).
     */
    public void triggerErroredUseCase(String errorDetails) {
        System.err.println("Errored use case triggered with details: " + errorDetails);
    }
}
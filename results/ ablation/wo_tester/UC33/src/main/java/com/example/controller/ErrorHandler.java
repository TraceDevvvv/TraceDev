package com.example.controller;

import com.example.dto.ValidationResult;

/**
 * Handles various error scenarios.
 * Added to satisfy the requirement from the sequence diagram.
 */
public class ErrorHandler {
    public void handleError(ValidationResult validationResult) {
        System.out.println("Error handled: " + validationResult.getErrors());
    }

    public void handleConnectionError() {
        System.out.println("Connection error handled.");
    }

    public void handleCancellation() {
        System.out.println("Cancellation handled.");
    }
}
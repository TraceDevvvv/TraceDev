package com.example.controller;

import com.example.validation.ValidationResult;
import com.example.exceptions.ConnectionException;

/**
 * Handles errors and exceptions.
 * Added to satisfy requirement REQ-9.
 */
public class ErrorHandler {
    public void triggerErroredUseCase(ValidationResult validationResult) {
        System.err.println("Validation error: " + validationResult.getErrorMessage());
        // Additional error handling logic could be added here.
    }

    public void handleConnectionError(ConnectionException exception) {
        System.err.println("Connection error: " + exception.getMessage());
        // Additional connection error handling logic.
    }
}
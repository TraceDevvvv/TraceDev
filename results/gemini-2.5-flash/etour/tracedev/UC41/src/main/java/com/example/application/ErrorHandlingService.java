package com.example.application;

import com.example.domain.NetworkConnectionException;

/**
 * Service for centralizing error handling logic.
 * Added to satisfy R19.
 */
public class ErrorHandlingService {

    /**
     * Handles network connection errors and returns a user-friendly message.
     * @param exception The network connection exception.
     * @return A localized or user-friendly error message.
     */
    public String handleNetworkError(NetworkConnectionException exception) {
        System.err.println("[ErrorHandlingService] Handling network error: " + exception.getMessage());
        // In a real application, this might involve looking up a message key,
        // transforming the error, or logging.
        return "A network connection error occurred: " + exception.getMessage() + ". Please try again later.";
    }

    /**
     * Handles general validation failures.
     * @return A user-friendly error message for validation.
     */
    public String handleValidationFailure() {
        System.err.println("[ErrorHandlingService] Handling validation failure.");
        return "Validation failed. Please check your input and try again.";
    }
}
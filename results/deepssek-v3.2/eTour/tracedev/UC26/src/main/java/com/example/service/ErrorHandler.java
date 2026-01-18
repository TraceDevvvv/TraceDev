package com.example.service;

/**
 * Handles different types of errors.
 */
public class ErrorHandler {
    /**
     * Handles validation errors.
     * @param message the error message
     */
    public void handleValidationError(String message) {
        System.out.println("Validation Error: " + message);
    }

    /**
     * Handles connection errors.
     */
    public void handleConnectionError() {
        System.out.println("Connection Error: Connection lost");
    }

    /**
     * Handles general errors.
     * @param errorCode the error code
     */
    public void handleGeneralError(int errorCode) {
        System.out.println("General Error with code: " + errorCode);
    }
}
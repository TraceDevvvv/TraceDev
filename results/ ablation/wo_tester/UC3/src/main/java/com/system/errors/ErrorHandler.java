package com.system.errors;

import java.util.List;

/**
 * Handles various types of errors in the system.
 */
public class ErrorHandler {
    /**
     * Handles validation errors by displaying them.
     */
    public void handleValidationError(List<String> errors) {
        System.out.println("Validation errors occurred:");
        for (String error : errors) {
            System.out.println(" - " + error);
        }
    }

    /**
     * Handles connection errors.
     */
    public void handleConnectionError() {
        System.out.println("Connection to server lost. Please check your network and try again.");
    }

    /**
     * Handles connection lost scenario.
     */
    public void connectionLost() {
        System.out.println("Connection lost detected.");
        handleConnectionError();
    }

    /**
     * Displays connection error to user.
     */
    public void displayConnectionError() {
        System.out.println("Display connection error: Unable to connect to server.");
    }

    /**
     * Handles system (unexpected) errors.
     */
    public void handleSystemError(Exception error) {
        System.out.println("System error: " + error.getMessage());
        error.printStackTrace();
    }

    /**
     * Indicates error has been handled (for sequence diagram traceability).
     */
    public boolean errorHandled() {
        return true;
    }
}
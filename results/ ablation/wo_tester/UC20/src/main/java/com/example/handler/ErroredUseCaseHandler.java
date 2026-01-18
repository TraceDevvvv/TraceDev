package com.example.handler;

/**
 * Handler for error use cases.
 */
public class ErroredUseCaseHandler {
    /**
     * Handles an error.
     */
    public void handleError(Exception error) {
        System.out.println("Handling error: " + error.getMessage());
        logError(error);
    }

    /**
     * Logs an error.
     */
    public void logError(Exception error) {
        System.out.println("Logging error: " + error.getClass().getSimpleName() + " - " + error.getMessage());
        // In a real implementation, this would write to a log file
    }
}
package com.example.error;

/**
 * trace(R13)
 * Quality requirement: Robust error handling
 * Centralized error handling for database and connection errors.
 */
public class ErrorHandler {
    public String handleDatabaseError(Exception exception) {
        String errorMsg = "Database error: " + exception.getMessage();
        logError(errorMsg);
        return errorMsg;
    }

    public String handleConnectionError(Exception exception) {
        String errorMsg = "Connection to server lost";
        logError(errorMsg + " - " + exception.getMessage());
        return errorMsg;
    }

    public void logError(String error) {
        System.err.println("ERROR LOG: " + error);
    }
}
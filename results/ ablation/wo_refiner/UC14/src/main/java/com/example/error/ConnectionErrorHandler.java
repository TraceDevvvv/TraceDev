package com.example.error;

/**
 * Handles connection errors with retry logic and logging.
 * Stereotype <<R12>>: Reliability and error handling for ETOUR server connections.
 */
public class ConnectionErrorHandler {
    private int maxRetries = 3;
    private boolean logErrorDetails = true;

    public void handleConnectionError(Exception error) {
        logError(error);
        // Simplified retry logic: just log retry attempts.
        for (int attempt = 1; shouldRetry(attempt); attempt++) {
            System.out.println("Retry attempt " + attempt + " for connection error: " + error.getMessage());
        }
        System.out.println("Max retries reached. Giving up.");
    }

    public boolean shouldRetry(int attempt) {
        return attempt <= maxRetries;
    }

    public void logError(Exception error) {
        if (logErrorDetails) {
            System.err.println("Connection error: " + error.getClass().getName() + " - " + error.getMessage());
            error.printStackTrace();
        } else {
            System.err.println("Connection error occurred.");
        }
    }
}
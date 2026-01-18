package com.example;

/**
 * Handles errors and returns user‑friendly messages.
 */
public class ErrorHandler {
    public String handleRepositoryError(Exception error) {
        // Map specific exceptions to messages
        if (error.getMessage().contains("NotFoundException")) {
            return "Tourist not found";
        } else if (error.getMessage().contains("SaveException")) {
            return "Failed to save tourist";
        } else if (error.getMessage().contains("RepositoryException")) {
            return "Repository error occurred";
        }
        return "An unexpected repository error occurred: " + error.getMessage();
    }

    public String handleConnectionError(Exception error) {
        return "Connection lost during operation";
    }
}
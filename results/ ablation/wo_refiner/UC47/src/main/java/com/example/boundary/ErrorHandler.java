package com.example.boundary;

import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Handles validation and connection errors (trace REQ-009, REQ-014).
 */
public class ErrorHandler {
    /**
     * Handle validation errors (REQ-009).
     */
    public void handleValidationError(List<String> errors) {
        System.err.println("Validation errors: " + errors);
        // Could log or send to monitoring system
    }

    /**
     * Handle connection errors and suggest retry (REQ-014).
     */
    public void handleConnectionError(Exception exception) {
        System.err.println("Connection error: " + exception.getMessage());
        if (exception instanceof ConnectionException) {
            ConnectionException ce = (ConnectionException) exception;
            int retryAfter = ce.getRetryAfter();
            System.out.println("Suggested retry after " + retryAfter + " seconds.");
        }
    }

    // Sequence diagram messages
    public void errorHandled() {
        System.out.println("Error handled successfully.");
    }

    public void retryRecommendation() {
        System.out.println("Recommendation: Retry the operation after some time.");
    }
}
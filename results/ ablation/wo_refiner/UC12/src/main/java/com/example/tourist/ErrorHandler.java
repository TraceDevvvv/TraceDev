package com.example.tourist;

import java.util.function.Supplier;

/**
 * Handles errors and provides retry mechanisms per REQ-010.
 */
public class ErrorHandler {
    /**
     * Handles connection errors by logging and providing user-friendly messages.
     * @param error the error description
     * @return a message indicating the error handling result
     */
    public String handleConnectionError(String error) {
        System.out.println("Connection error handled: " + error);
        return "Retrying operation";
    }

    /**
     * Handles connection failure and returns retry message as per sequence diagram m12
     * @return "Retrying operation" as per sequence diagram
     */
    public String handleConnectionFailed() {
        System.out.println("Connection failed - handling error");
        return "Retrying operation";
    }

    /**
     * Retries an operation up to a specified number of times.
     * @param operation the operation to retry (as a Supplier)
     * @param maxRetries maximum number of retry attempts
     * @return true if operation succeeds within retries, false otherwise
     */
    public boolean retryOperation(Supplier<Boolean> operation, int maxRetries) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            System.out.println("Retry attempt " + attempt + " of " + maxRetries);
            try {
                if (operation.get()) {
                    System.out.println("Operation succeeded on attempt " + attempt);
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Operation failed on attempt " + attempt + ": " + e.getMessage());
            }
            
            // Wait before next retry (simulate delay)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        System.out.println("All retry attempts failed");
        return false;
    }
}
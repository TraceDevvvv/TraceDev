package com.example;

/**
 * Utility service for handling exceptions.
 * Added to satisfy requirement R13.
 * Annotated with <<Utility>> stereotype.
 */
public class ErrorHandlerService {

    /**
     * Handles a given exception, typically by logging it or displaying an error message.
     * For this implementation, it simply prints the stack trace to standard error.
     *
     * @param ex The exception to handle.
     */
    public void handleException(Exception ex) {
        System.err.println("ERROR: An exception occurred.");
        ex.printStackTrace(); // Log the exception for debugging
        // In a real application, this would involve more sophisticated logging
        // or error reporting mechanisms.
    }
}
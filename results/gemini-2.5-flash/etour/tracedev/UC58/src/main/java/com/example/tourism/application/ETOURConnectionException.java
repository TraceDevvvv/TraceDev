package com.example.tourism.application;

/**
 * Custom Exception: Represents an error occurring during connection to an external service (e.g., ETOUR).
 * Implemented to satisfy Audit Recommendation for REQ-EXC02.
 */
public class ETOURConnectionException extends Exception {

    /**
     * Constructs a new ETOURConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ETOURConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ETOURConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
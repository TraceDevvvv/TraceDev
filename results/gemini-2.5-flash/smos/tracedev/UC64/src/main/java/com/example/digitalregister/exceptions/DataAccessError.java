package com.example.digitalregister.exceptions;

/**
 * Custom exception to represent errors during data access operations,
 * such as connection interruptions to external data sources.
 */
public class DataAccessError extends RuntimeException {
    /**
     * Constructs a new DataAccessError with the specified detail message.
     * @param message The detail message.
     */
    public DataAccessError(String message) {
        super(message);
    }

    /**
     * Constructs a new DataAccessError with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public DataAccessError(String message, Throwable cause) {
        super(message, cause);
    }
}
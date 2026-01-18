package com.example.exceptions;

/**
 * Custom exception for ETOUR service related errors, as per R11.
 */
public class ETOURServiceException extends Exception {

    /**
     * Constructs a new ETOURServiceException with the specified detail message.
     * @param message the detail message.
     */
    public ETOURServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new ETOURServiceException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public ETOURServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
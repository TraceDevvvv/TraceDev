package com.example;

/**
 * Custom exception to represent failures when interacting with external systems.
 * This is used specifically for the ParentEmailSystem simulation.
 */
public class ExternalSystemException extends Exception {

    /**
     * Constructs a new ExternalSystemException with the specified detail message.
     *
     * @param message The detail message.
     */
    public ExternalSystemException(String message) {
        super(message);
    }

    /**
     * Constructs a new ExternalSystemException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public ExternalSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
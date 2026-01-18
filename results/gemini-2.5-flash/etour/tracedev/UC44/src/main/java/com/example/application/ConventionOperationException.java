package com.example.application;

/**
 * Custom runtime exception for application-level operational failures in Convention-related use cases.
 * This provides a way to signal specific business or operational errors from the service layer to the presentation layer.
 */
public class ConventionOperationException extends RuntimeException {

    /**
     * Constructs a new ConventionOperationException with the specified detail message.
     *
     * @param message The detail message.
     */
    public ConventionOperationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConventionOperationException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause.
     */
    public ConventionOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
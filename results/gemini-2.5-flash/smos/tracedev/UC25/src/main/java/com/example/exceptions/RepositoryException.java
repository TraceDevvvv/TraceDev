package com.example.exceptions;

/**
 * Custom exception for repository operations.
 * Extends standard Java Exception.
 */
public class RepositoryException extends Exception {
    /**
     * Constructs a new RepositoryException with the specified detail message.
     * @param message the detail message.
     */
    public RepositoryException(String message) {
        super(message);
    }

    /**
     * Constructs a new RepositoryException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
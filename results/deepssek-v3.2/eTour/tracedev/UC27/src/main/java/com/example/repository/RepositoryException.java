package com.example.repository;

/**
 * Exception thrown by the repository in case of errors (e.g., connection interruption).
 */
public class RepositoryException extends RuntimeException {
    /**
     * Constructs a RepositoryException with the specified message and cause.
     * @param message the error message
     * @param cause the cause
     */
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
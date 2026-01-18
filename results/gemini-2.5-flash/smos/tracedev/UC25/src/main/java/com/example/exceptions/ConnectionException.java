package com.example.exceptions;

/**
 * Custom exception indicating a connection interruption,
 * specifically for repository operations.
 * Extends RepositoryException.
 */
public class ConnectionException extends RepositoryException {
    /**
     * Constructs a new ConnectionException with the specified detail message.
     * @param message the detail message.
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
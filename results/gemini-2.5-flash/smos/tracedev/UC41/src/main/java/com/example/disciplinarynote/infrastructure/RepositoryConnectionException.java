package com.example.disciplinarynote.infrastructure;

/**
 * Custom exception for repository connection issues.
 * Added to satisfy requirement R11.
 */
public class RepositoryConnectionException extends Exception {
    /**
     * Constructs a new RepositoryConnectionException with the specified detail message.
     * @param message The detail message.
     */
    public RepositoryConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new RepositoryConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause.
     */
    public RepositoryConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.example.absence.infrastructure;

/**
 * Exception thrown when a persistence operation fails.
 */
public class PersistenceException extends Exception {
    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
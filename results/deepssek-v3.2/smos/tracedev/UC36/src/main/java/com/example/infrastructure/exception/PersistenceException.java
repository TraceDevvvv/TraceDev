package com.example.infrastructure.exception;

/**
 * Exception thrown on persistence failures.
 */
public class PersistenceException extends RuntimeException {
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
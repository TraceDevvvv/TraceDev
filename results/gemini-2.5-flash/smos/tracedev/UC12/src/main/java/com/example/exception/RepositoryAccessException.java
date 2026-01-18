package com.example.exception;

/**
 * Custom exception for data access layer errors,
 * typically indicating issues with database connectivity or operations.
 */
public class RepositoryAccessException extends Exception {
    public RepositoryAccessException(String message) {
        super(message);
    }

    public RepositoryAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
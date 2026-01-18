package com.example.exceptions;

/**
 * Custom exception for issues encountered during data access operations (e.g., repository layer).
 * Part of the Data Access Layer's error handling.
 */
public class DataAccessException extends Exception {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
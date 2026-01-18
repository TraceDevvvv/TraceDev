package com.example.statistics;

/**
 * Custom exception for data access layer errors.
 * Used to signal issues when interacting with the underlying data source (e.g., database).
 */
public class DataAccessException extends Exception {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
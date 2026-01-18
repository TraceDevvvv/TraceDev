package com.example.infrastructure;

/**
 * Exception thrown when data access fails (e.g., due to connection issues).
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(String message) {
        super(message);
    }
}
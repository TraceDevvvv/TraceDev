package com.example.exceptions;

/**
 * Exception thrown for issues related to data access, typically from the repository layer.
 */
public class DataAccessException extends Exception {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.example.exception;

/**
 * Exception for data access layer errors.
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
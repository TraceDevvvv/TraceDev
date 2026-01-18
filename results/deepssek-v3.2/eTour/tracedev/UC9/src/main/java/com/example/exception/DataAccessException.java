package com.example.exception;

/**
 * Exception thrown when data access fails (e.g., database connection error).
 */
public class DataAccessException extends Exception {
    private String message;

    public DataAccessException(String message) {
        super(message);
        this.message = message;
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
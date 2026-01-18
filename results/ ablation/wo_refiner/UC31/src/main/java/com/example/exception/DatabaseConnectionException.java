package com.example.exception;

/**
 * Custom exception for database connection failures.
 * Traceability: Satisfies R12 (Handles connection interruption scenarios)
 */
public class DatabaseConnectionException extends RuntimeException {
    private String message;
    
    public DatabaseConnectionException(String message) {
        super(message);
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
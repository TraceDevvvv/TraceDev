package com.example.account;

/**
 * Custom exception for database-related errors.
 * (Added to satisfy REQ-016 for specific error handling in the sequence diagram)
 */
public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
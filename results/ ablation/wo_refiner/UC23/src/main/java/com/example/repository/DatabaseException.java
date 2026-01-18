package com.example.repository;

/**
 * Custom exception for database-related errors.
 */
public class DatabaseException extends Exception {
    private String errorCode;
    private String message;

    public DatabaseException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
package com.example.exception;

/**
 * Custom exception for database-related errors.
 */
public class DatabaseException extends Exception {
    private String message;
    private int errorCode;

    public DatabaseException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
package com.example.domain;

/**
 * Exception thrown when a database operation fails.
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
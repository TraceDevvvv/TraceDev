package com.example.domain;

/**
 * Exception thrown when a persistence operation fails (e.g., connection interrupted).
 */
public class PersistenceException extends Exception {
    private String message;
    private int errorCode;

    public PersistenceException(String message, int errorCode) {
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
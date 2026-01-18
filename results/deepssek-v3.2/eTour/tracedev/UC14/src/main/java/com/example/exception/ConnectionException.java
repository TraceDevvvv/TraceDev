package com.example.exception;

/**
 * Exception thrown when connection to external server fails.
 */
public class ConnectionException extends Exception {
    private String message;

    public ConnectionException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
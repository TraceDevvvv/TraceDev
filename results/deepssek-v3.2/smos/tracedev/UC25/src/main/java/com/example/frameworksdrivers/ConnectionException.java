package com.example.frameworksdrivers;

/**
 * Exception thrown when connection to SMOS server fails.
 */
public class ConnectionException extends RuntimeException {
    private final String message;

    public ConnectionException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
package com.example.exceptions;

/**
 * Exception for connection errors.
 */
public class ConnectionException extends RuntimeException {
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
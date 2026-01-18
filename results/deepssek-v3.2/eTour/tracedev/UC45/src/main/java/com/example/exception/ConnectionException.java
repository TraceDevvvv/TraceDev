package com.example.exception;

/**
 * Exception for database connection failures.
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
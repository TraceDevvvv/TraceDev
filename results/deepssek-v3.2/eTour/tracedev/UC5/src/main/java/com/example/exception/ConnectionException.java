package com.example.exception;

/**
 * Exception thrown when a connection error occurs, e.g., database or remote service failure.
 * As per class diagram, it has a message field.
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

    public void setMessage(String message) {
        this.message = message;
    }
}
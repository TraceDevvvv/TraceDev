package com.example;

/**
 * Custom exception for connection-related issues.
 * This is thrown when there is an interruption in the connection to the server.
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
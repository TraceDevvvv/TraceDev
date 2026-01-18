package com.example;

/**
 * Exception thrown when there is a connection interruption to the SMOS server.
 * Traceability: Connection to SMOS server interruption.
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
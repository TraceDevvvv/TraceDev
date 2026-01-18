package com.example.login;

/**
 * Custom exception to simulate connection errors, as per REQ-ETOUR.
 */
public class ConnectionException extends RuntimeException {
    /**
     * Constructs a new ConnectionException with the specified detail message.
     * @param message the detail message.
     */
    public ConnectionException(String message) {
        super(message);
    }
}
package com.example.app;

/**
 * Custom exception for connection-related issues,
 * as specified in requirement R12.
 */
public class ConnectionException extends Exception {

    /**
     * Constructs a new ConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public ConnectionException(String message) {
        super(message);
    }
}
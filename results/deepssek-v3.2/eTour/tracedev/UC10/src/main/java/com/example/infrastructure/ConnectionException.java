package com.example.infrastructure;

/**
 * Exception thrown when connection to the ETOUR server fails.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }
}
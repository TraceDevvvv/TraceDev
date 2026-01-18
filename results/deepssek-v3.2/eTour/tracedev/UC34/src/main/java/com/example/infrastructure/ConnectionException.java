package com.example.infrastructure;

/**
 * Exception thrown when connection fails.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }
}
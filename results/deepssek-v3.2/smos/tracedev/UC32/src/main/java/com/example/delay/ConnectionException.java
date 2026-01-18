package com.example.delay;

/**
 * Exception thrown when server connection fails.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }
}
package com.example.exception;

/**
 * Custom exception for server connection failures.
 * Added to satisfy Exit Conditions.
 */
public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }
}
package com.example.exception;

/**
 * Exception for connection failures.
 * Used in sequence diagram alternative flow.
 */
public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }
    
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
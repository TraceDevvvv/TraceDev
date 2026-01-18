package com.example.exception;

/**
 * Exception thrown when connection issues occur.
 */
public class ConnectionException extends Exception {
    
    public ConnectionException(String message) {
        super(message);
    }
    
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
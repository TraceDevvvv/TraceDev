package com.example.data;

/**
 * Custom exception for database connection errors.
 * Used to simulate server interruption as per the Sequence Diagram.
 */
public class ConnectionException extends RuntimeException {
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
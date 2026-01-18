package com.example.studentdelay.util;

/**
 * Custom exception for connection failures, specifically with external systems like SMOS.
 */
public class ConnectionException extends RuntimeException {
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(String message) {
        super(message);
    }
}
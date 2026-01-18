package com.example.exceptions;

/**
 * Custom exception for issues related to external system connections (e.g., SMOSClient).
 * Part of the Data Access Layer's error handling.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
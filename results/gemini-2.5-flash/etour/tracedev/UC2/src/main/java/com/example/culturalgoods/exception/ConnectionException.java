package com.example.culturalgoods.exception;

/**
 * Custom exception for indicating a connection issue to an external system or database.
 * Satisfies requirement: Exit Condition: Connection to ETOUR server interrupted.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
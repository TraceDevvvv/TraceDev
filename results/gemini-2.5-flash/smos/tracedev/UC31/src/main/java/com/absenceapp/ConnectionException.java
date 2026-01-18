package com.absenceapp;

/**
 * Custom exception for connection-related errors, e.g., with external systems.
 * REQ-002: Extends PersistenceException.
 */
public class ConnectionException extends PersistenceException {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
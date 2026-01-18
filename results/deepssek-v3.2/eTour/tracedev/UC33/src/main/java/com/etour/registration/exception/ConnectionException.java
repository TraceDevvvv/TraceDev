package com.etour.registration.exception;

/**
 * Exception thrown when connection to the server/database is interrupted.
 */
public class ConnectionException extends PersistenceException {
    public ConnectionException(String message) {
        super(message);
    }
}
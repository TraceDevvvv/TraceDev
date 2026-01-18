package com.example.newsapp.common;

/**
 * Custom checked exception to simulate connection issues with the ETOUR server.
 * This exception is thrown by data access layer components when a connection
 * problem occurs, as specified in the sequence diagram's exit conditions.
 */
public class ConnectionException extends Exception {

    /**
     * Constructs a new ConnectionException with the specified detail message.
     *
     * @param message the detail message.
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
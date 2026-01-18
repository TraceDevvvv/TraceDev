package com.example.bookmark;

/**
 * Custom exception to represent a connection error, as specified in REQ-001.
 */
public class ConnectionException extends Exception {
    /**
     * Constructs a new ConnectionException with the specified detail message.
     * @param message the detail message.
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
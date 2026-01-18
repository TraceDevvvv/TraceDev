package com.example.notessystem.exception;

/**
 * Custom exception to represent issues with connecting to an external service or database.
 * Used to simulate the "Connection to SMOS server interrupted" scenario in the sequence diagram.
 */
public class ConnectionException extends RuntimeException {

    /**
     * Constructs a new ConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
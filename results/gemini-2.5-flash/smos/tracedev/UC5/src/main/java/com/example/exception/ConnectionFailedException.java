package com.example.exception;

/**
 * Custom unchecked exception to represent a failure in connecting to an external system.
 * This is used to simulate the "Connection to SMOS server interrupted" scenario in the sequence diagram.
 */
public class ConnectionFailedException extends RuntimeException {

    /**
     * Constructs a new ConnectionFailedException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionFailedException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionFailedException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public ConnectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
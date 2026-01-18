package com.example;

/**
 * Custom exception to represent issues with database or service connections.
 * This is used to model the 'ETOUR' (connection interrupted) exit condition.
 */
public class ConnectionException extends Exception {

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
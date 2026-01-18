package com.example.app.exception;

/**
 * Custom exception class representing a connection error,
 * as specified by R7 in the Class Diagram.
 */
public class ConnectionError extends Exception {

    /**
     * Constructs a new ConnectionError with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionError(String message) {
        super(message);
    }
}
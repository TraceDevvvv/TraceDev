package com.example.exception;

/**
 * Custom exception to indicate that a connection to an external resource (e.g., database) was lost.
 * This is used to model the "ConnectionLostError" from the sequence diagram.
 */
public class ConnectionLostException extends RuntimeException {
    public ConnectionLostException(String message) {
        super(message);
    }
}
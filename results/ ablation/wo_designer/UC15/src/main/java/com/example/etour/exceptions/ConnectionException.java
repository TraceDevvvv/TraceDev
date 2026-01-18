package com.example.etour.exceptions;

/**
 * Exception thrown when connection to the server is interrupted.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }
}
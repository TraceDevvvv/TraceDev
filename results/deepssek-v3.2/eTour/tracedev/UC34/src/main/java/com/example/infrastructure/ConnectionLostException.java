package com.example.infrastructure;

/**
 * Exception thrown when connection is lost during a query.
 */
public class ConnectionLostException extends Exception {
    public ConnectionLostException(String message) {
        super(message);
    }
}
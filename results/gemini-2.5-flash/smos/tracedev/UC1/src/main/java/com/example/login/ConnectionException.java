package com.example.login;

/**
 * Exception Class: Thrown when there is an issue connecting to a data source or external service.
 * // Added to satisfy requirement R13, R14
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }
}
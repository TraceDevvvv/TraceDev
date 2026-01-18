package com.example.service;

/**
 * Custom exception to represent network connection issues.
 * This is used to satisfy the requirement: Exit Conditions: Interruption of the connection to the server.
 */
public class NetworkConnectionException extends Exception {

    /**
     * Constructs a new NetworkConnectionException with the specified detail message.
     * @param message the detail message.
     */
    public NetworkConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new NetworkConnectionException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public NetworkConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
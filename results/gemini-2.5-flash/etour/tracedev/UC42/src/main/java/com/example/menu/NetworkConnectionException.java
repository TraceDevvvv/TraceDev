package com.example.menu;

/**
 * REQ-R13: Custom exception to represent network connection issues.
 * Used for error handling traceability in the application.
 */
public class NetworkConnectionException extends Exception {
    /**
     * Constructs a new NetworkConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public NetworkConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new NetworkConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public NetworkConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
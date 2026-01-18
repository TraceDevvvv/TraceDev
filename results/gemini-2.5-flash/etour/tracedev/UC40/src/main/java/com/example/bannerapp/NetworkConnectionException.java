package com.example.bannerapp;

/**
 * Custom exception to simulate network or database connection issues.
 * This exception extends RuntimeException as specified in the class diagram.
 */
public class NetworkConnectionException extends RuntimeException {
    /**
     * Constructs a new NetworkConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public NetworkConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new NetworkConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public NetworkConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
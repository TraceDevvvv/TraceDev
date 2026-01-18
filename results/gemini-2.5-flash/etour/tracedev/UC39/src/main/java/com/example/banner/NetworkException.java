package com.example.banner;

/**
 * Custom exception to simulate network-related errors during data access.
 * Corresponds to requirement R17 in the sequence diagram.
 */
public class NetworkException extends Exception {
    /**
     * Constructs a new NetworkException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public NetworkException(String message) {
        super(message);
    }

    /**
     * Constructs a new NetworkException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     */
    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
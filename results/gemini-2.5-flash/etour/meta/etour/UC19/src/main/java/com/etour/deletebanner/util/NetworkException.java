package com.etour.deletebanner.util;

/**
 * Custom exception for network-related errors when communicating with the ETOUR server.
 */
public class NetworkException extends Exception {

    /**
     * Constructs a new NetworkException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public NetworkException(String message) {
        super(message);
    }

    /**
     * Constructs a new NetworkException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
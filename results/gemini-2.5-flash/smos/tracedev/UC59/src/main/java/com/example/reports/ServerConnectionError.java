package com.example.reports;

/**
 * Custom exception to simulate a server connection error.
 * Added to satisfy requirement ExC2.
 */
public class ServerConnectionError extends Exception {
    /**
     * Constructs a new ServerConnectionError with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public ServerConnectionError(String message) {
        super(message);
    }

    /**
     * Constructs a new ServerConnectionError with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public ServerConnectionError(String message, Throwable cause) {
        super(message, cause);
    }
}
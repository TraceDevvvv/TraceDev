package com.example.culturalassets.exception;

/**
 * Custom exception indicating a failure in connecting to a server or database.
 * This can be used to handle specific network or data source connection issues.
 */
public class ConnectionFailedException extends Exception {
    /**
     * Constructs a new ConnectionFailedException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionFailedException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionFailedException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
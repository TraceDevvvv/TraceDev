package com.example.culturalheritage;

/**
 * Custom exception to represent issues with the ETOUR server connection.
 * This exception is thrown when an operation requires a connection to the ETOUR server,
 * but the connection is interrupted or unavailable.
 */
public class ETOURConnectionException extends Exception {

    /**
     * Constructs a new ETOURConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ETOURConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ETOURConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
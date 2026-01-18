package com.etour.exceptions;

/**
 * Custom exception class to represent an interruption in the connection to the ETOUR server.
 * This exception is thrown when simulated network issues prevent data retrieval.
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
     * Constructs a new ETOURConnectionException with the specified detail message and
     * cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
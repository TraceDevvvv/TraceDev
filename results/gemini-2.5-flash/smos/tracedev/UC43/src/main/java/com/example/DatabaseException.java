package com.example;

/**
 * Custom exception indicating a general database access error.
 */
public class DatabaseException extends Exception {

    /**
     * Constructs a new DatabaseException with the specified detail message.
     *
     * @param message The detail message.
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * Constructs a new DatabaseException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
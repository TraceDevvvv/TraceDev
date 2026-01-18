package com.news.management.system.exception;

/**
 * Custom exception class for handling errors that occur during database operations.
 * This exception wraps lower-level SQLException or other data access issues.
 */
public class DatabaseOperationException extends Exception {

    /**
     * Constructs a new DatabaseOperationException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public DatabaseOperationException(String message) {
        super(message);
    }

    /**
     * Constructs a new DatabaseOperationException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.news.management.system.exception;

/**
 * Custom exception class for handling invalid or insufficient news data.
 * This exception is typically thrown by the service layer when validation rules are violated.
 */
public class InvalidNewsDataException extends Exception {

    /**
     * Constructs a new InvalidNewsDataException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidNewsDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidNewsDataException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidNewsDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
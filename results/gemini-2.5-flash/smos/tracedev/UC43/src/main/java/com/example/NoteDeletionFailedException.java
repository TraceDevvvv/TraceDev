package com.example;

/**
 * Custom exception indicating that a note deletion operation failed.
 * This can be due to various reasons such as note not found, database errors, etc.
 */
public class NoteDeletionFailedException extends Exception {

    /**
     * Constructs a new NoteDeletionFailedException with the specified detail message.
     *
     * @param message The detail message.
     */
    public NoteDeletionFailedException(String message) {
        super(message);
    }

    /**
     * Constructs a new NoteDeletionFailedException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public NoteDeletionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
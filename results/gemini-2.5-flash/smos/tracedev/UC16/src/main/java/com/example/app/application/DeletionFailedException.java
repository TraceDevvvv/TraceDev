package com.example.app.application;

/**
 * Custom exception to indicate that a deletion operation failed,
 * often due to data integrity issues or business rules violation.
 * Satisfies requirement REQ-002 from the sequence diagram.
 */
public class DeletionFailedException extends Exception {
    /**
     * Constructs a new DeletionFailedException with the specified detail message.
     * @param message The detail message.
     */
    public DeletionFailedException(String message) {
        super(message);
    }

    /**
     * Constructs a new DeletionFailedException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public DeletionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
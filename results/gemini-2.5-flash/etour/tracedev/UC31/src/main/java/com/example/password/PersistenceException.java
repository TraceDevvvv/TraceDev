package com.example.password;

/**
 * Custom exception to indicate errors related to data persistence (e.g., database connection issues).
 * This exception satisfies the requirement for exit conditions related to server connection interruption.
 */
public class PersistenceException extends Exception {
    /**
     * Constructs a new PersistenceException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Constructs a new PersistenceException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
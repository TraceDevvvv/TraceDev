package com.example.app;

/**
 * PersistenceException is a custom runtime exception indicating a failure
 * during a persistence operation (e.g., saving data to a repository/database).
 * This acts as the 'PersistenceError' shown in the Sequence Diagram.
 */
public class PersistenceException extends RuntimeException {

    /**
     * Constructs a new PersistenceException with a detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Constructs a new PersistenceException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     * @param cause   The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *                (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
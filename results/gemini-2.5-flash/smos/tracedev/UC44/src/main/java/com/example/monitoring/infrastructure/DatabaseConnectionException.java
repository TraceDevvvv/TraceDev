package com.example.monitoring.infrastructure;

/**
 * Infrastructure Layer: Custom exception to signify issues with database connectivity.
 * This is an unchecked exception (extends RuntimeException) as database connection
 * issues are often unrecoverable at the point of call and should propagate up
 * to a higher layer (e.g., UI or global error handler) to inform the user or administrator.
 */
public class DatabaseConnectionException extends RuntimeException {

    /**
     * Constructs a new DatabaseConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public DatabaseConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new DatabaseConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
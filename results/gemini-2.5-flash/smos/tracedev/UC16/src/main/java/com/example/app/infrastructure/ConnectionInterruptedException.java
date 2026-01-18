package com.example.app.infrastructure;

/**
 * Custom exception to indicate that a connection to the archive/database
 * was interrupted or failed.
 * Satisfies requirement REQ-001 from the sequence diagram.
 */
public class ConnectionInterruptedException extends Exception {
    /**
     * Constructs a new ConnectionInterruptedException with the specified detail message.
     * @param message The detail message.
     */
    public ConnectionInterruptedException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionInterruptedException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public ConnectionInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.example.banner.infrastructure;

/**
 * Custom exception to simulate connection issues with an external ETOUR system.
 * This is used to model potential failure points during database operations
 * as described in REQ-ExitCondition-002 of the sequence diagram.
 */
public class ETOURConnectionException extends Exception {
    /**
     * Constructs a new ETOURConnectionException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public ETOURConnectionException(String message) {
        super(message);
    }
}
package com.example.agencyapp.domain;

/**
 * Custom exception indicating a connection issue with the ETOUR system.
 * This exception is specifically added to satisfy requirement REQ-012.
 */
public class ETOURConnectionException extends Exception {
    /**
     * Constructs a new ETOURConnectionException with the specified detail message.
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ETOURConnectionException(String message) {
        super(message);
    }
}
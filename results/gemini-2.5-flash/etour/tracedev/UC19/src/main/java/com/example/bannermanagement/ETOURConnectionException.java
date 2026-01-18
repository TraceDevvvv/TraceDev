package com.example.bannermanagement;

/**
 * Custom exception to indicate a connection issue with the ETOUR server.
 * This class is added to satisfy requirement REQ-003.
 */
public class ETOURConnectionException extends RuntimeException {
    /**
     * Constructs a new ETOURConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ETOURConnectionException(String message) {
        super(message);
    }
}
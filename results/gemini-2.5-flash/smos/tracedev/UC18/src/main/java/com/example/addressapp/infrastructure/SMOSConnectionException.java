package com.example.addressapp.infrastructure;

/**
 * Custom exception to represent a connection error to the SMOS server.
 * This exception is typically thrown by the infrastructure layer, e.g., by repositories
 * when unable to connect to the persistence store.
 */
public class SMOSConnectionException extends Exception {

    /**
     * Constructs a new SMOSConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public SMOSConnectionException(String message) {
        super(message);
    }
}
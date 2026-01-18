package com.example.smos.exception;

/**
 * Custom exception to represent connection failures to the SMOS server or its underlying database.
 * Corresponds to the 'SMOSConnectionException' class in the UML Class Diagram.
 */
public class SMOSConnectionException extends Exception {

    /**
     * Constructs a new SMOSConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public SMOSConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new SMOSConnectionException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
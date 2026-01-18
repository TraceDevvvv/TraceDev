package com.smos.management.exceptions;

/**
 * Custom exception to represent connection issues with the SMOS server or data access layer.
 * This class extends Exception as specified in the Class Diagram.
 */
public class SMOSConnectionException extends Exception {

    /**
     * Constructs a new SMOSConnectionException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public SMOSConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new SMOSConnectionException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.example.teachingsystem.exception;

/**
 * Custom exception to be thrown when there are issues connecting to or interacting with the SMOS server.
 * This exception indicates a problem with the external SMOS integration.
 */
public class SMOSConnectionException extends RuntimeException {

    /**
     * Constructs a new SMOSConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public SMOSConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new SMOSConnectionException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
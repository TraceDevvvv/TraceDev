package com.example.school;

/**
 * Custom exception for SMOS server connection issues.
 * Added to satisfy requirement ExC2.
 */
public class SMOSConnectionException extends Exception {

    /**
     * Constructs a new SMOSConnectionException with the specified detail message.
     * @param message the detail message.
     */
    public SMOSConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new SMOSConnectionException with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.example.schoolreports;

/**
 * Custom exception class for SMOS connection issues.
 * This exception is thrown when there are problems communicating with the SMOS server.
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
}
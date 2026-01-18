package com.example;

/**
 * Custom exception for SMOS connection failures.
 * Added to satisfy requirement R15.
 */
public class SMOSConnectionException extends RuntimeException {

    /**
     * Constructs a new SMOSConnectionException with the specified detail message.
     * @param message The detail message.
     */
    public SMOSConnectionException(String message) {
        super(message);
    }
}
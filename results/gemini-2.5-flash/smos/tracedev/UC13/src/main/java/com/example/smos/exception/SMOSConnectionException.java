package com.example.smos.exception;

/**
 * Custom exception to simulate a connection failure to the SMOS server,
 * as specified in R12 of the sequence diagram.
 */
public class SMOSConnectionException extends RuntimeException {
    public SMOSConnectionException(String message) {
        super(message);
    }

    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
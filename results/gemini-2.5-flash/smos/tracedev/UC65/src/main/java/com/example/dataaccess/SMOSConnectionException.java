package com.example.dataaccess;

/**
 * Custom exception to indicate an error connecting to the SMOS server.
 */
public class SMOSConnectionException extends RuntimeException {
    public SMOSConnectionException(String message) {
        super(message);
    }

    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
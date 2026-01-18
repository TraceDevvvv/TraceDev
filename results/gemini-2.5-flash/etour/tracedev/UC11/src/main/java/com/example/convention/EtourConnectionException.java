package com.example.convention;

/**
 * Custom exception to indicate that the connection to the ETOUR system failed.
 * This is used to handle REQ7: ETOUR connection interruption.
 */
public class EtourConnectionException extends RuntimeException {
    public EtourConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtourConnectionException(String message) {
        super(message);
    }
}
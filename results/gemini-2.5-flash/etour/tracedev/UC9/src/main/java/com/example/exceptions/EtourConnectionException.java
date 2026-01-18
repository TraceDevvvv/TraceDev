package com.example.exceptions;

/**
 * Exception thrown when there's an issue connecting to the ETOUR external service.
 */
public class EtourConnectionException extends Exception {
    public EtourConnectionException(String message) {
        super(message);
    }

    public EtourConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
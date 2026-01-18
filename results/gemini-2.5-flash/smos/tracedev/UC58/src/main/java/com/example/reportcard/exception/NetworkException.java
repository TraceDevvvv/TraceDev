package com.example.reportcard.exception;

/**
 * Custom exception to indicate a network connectivity issue.
 * Used for error handling as specified in the sequence diagram.
 */
public class NetworkException extends RuntimeException {
    public NetworkException(String message) {
        super(message);
    }
}
package com.restaurant.exception;

/**
 * Custom exception to simulate server connection interruption.
 */
public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }
}
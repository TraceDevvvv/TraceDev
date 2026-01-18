package com.example.exception;

/**
 * Custom exception for connection interruptions.
 * Added to satisfy requirement Exit Conditions.
 */
public class ConnectionLostException extends RuntimeException {
    public ConnectionLostException(String message) {
        super(message);
    }

    public ConnectionLostException(String message, Throwable cause) {
        super(message, cause);
    }
}
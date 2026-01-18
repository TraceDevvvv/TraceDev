package com.example.exception;

/**
 * Custom exception to indicate a connection failure.
 * Added to satisfy requirement: Exit Conditions: The connection to the server ETOUR is interrupted
 */
public class ConnectionException extends Exception {
    private final int errorCode;

    public ConnectionException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
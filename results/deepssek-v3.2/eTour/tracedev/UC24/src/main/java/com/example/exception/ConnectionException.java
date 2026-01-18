package com.example.exception;

/**
 * Custom exception for connection interruptions.
 * Represents the Exit Conditions requirement.
 */
public class ConnectionException extends Exception {
    private int errorCode;
    private String message;

    public ConnectionException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public ConnectionException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
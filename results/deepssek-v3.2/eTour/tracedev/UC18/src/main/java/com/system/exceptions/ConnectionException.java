package com.system.exceptions;

/**
 * Exception raised when server connection fails.
 * Exit Condition: Server connection interruption handling.
 */
public class ConnectionException extends RuntimeException {
    private String errorCode;
    private String message;

    public ConnectionException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
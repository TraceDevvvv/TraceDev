package com.example.exception;

/**
 * Custom exception to represent connection failures.
 */
public class ConnectionException extends Exception {
    private String message;
    private int errorCode;

    public ConnectionException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
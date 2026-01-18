package com.example.exception;

/**
 * Represents a network-related exception.
 * Added to satisfy requirement Exit Conditions: connection loss handling
 */
public class NetworkException extends RuntimeException {
    private String message;
    private int errorCode;

    public NetworkException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getDetails() {
        return "Error Code: " + errorCode + ", Message: " + message;
    }

    // Getters and setters
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
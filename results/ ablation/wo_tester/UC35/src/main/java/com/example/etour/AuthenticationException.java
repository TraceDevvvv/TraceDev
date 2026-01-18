package com.example.etour;

/**
 * Custom exception for authentication errors.
 */
public class AuthenticationException extends RuntimeException {
    private String errorCode;
    private String message;

    public AuthenticationException(String errorCode, String message) {
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
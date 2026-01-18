package com.example.exception;

/**
 * Exception thrown when a service layer operation fails.
 */
public class ServiceException extends Exception {
    private String message;
    private Throwable cause;

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }
}
package com.example.exception;

/**
 * Exception for service layer errors.
 */
public class ServiceException extends RuntimeException {

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
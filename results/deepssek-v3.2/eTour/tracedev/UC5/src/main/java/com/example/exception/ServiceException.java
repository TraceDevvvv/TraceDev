package com.example.exception;

/**
 * Exception thrown by serv to wrap lower-level exceptions like ConnectionException.
 * As per class diagram, it has a message and a cause.
 */
public class ServiceException extends Exception {
    private String message;
    private Exception cause;

    public ServiceException(String message, Exception cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Exception getCause() {
        return cause;
    }

    public void setCause(Exception cause) {
        this.cause = cause;
    }
}
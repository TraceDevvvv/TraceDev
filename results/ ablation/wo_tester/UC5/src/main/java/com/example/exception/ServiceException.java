package com.example.exception;

/**
 * Exception thrown by service layer.
 */
public class ServiceException extends Exception {
    private String message;
    
    public ServiceException(String message) {
        super(message);
        this.message = message;
    }
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
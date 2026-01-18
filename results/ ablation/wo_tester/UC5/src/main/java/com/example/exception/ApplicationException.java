package com.example.exception;

/**
 * Exception thrown by application/controller layer.
 */
public class ApplicationException extends Exception {
    private String message;
    
    public ApplicationException(String message) {
        super(message);
        this.message = message;
    }
    
    public ApplicationException(String message, Throwable cause) {
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
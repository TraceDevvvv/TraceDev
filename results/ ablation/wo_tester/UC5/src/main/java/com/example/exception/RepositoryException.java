package com.example.exception;

/**
 * Exception thrown by repository layer.
 */
public class RepositoryException extends Exception {
    private String message;
    
    public RepositoryException(String message) {
        super(message);
        this.message = message;
    }
    
    public RepositoryException(String message, Throwable cause) {
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
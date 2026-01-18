package com.example.exception;

/**
 * Exception thrown when search operation fails.
 * Can wrap other exceptions like ConnectionException.
 */
public class SearchException extends RuntimeException {
    private String message;
    private Exception cause;
    
    public SearchException(String message, Exception cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Exception getCause() {
        return cause;
    }
}
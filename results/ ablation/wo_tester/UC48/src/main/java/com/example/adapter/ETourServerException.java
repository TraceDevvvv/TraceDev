package com.example.adapter;

/**
 * Exception for ETour server errors.
 */
public class ETourServerException extends RuntimeException {
    private String message;
    private int errorCode;
    
    public ETourServerException(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
    
    public String getMessage() {
        return message;
    }
    
    public int getErrorCode() {
        return errorCode;
    }
}
package com.example.domain.exceptions;

public class ETOURConnectionException extends RuntimeException {
    private String message;
    
    public ETOURConnectionException(String message) {
        super(message);
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}
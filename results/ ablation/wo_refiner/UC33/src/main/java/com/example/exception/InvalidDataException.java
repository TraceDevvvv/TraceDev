package com.example.exception;

/**
 * Exception thrown when registration data is invalid.
 */
public class InvalidDataException extends Exception {
    
    public InvalidDataException(String message) {
        super(message);
    }
    
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
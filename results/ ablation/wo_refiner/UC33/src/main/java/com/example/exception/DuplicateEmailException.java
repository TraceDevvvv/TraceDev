package com.example.exception;

/**
 * Exception thrown when email already exists.
 */
public class DuplicateEmailException extends Exception {
    
    public DuplicateEmailException(String message) {
        super(message);
    }
    
    public DuplicateEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.example.exception;

/**
 * Exception for service unavailability.
 * Used in sequence diagram alternative flow.
 */
public class ServiceUnavailableException extends Exception {
    public ServiceUnavailableException(String message) {
        super(message);
    }
    
    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
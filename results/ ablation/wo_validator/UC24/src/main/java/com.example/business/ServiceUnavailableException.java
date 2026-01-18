package com.example.business;

/**
 * Exception thrown when a service (e.g., database) is unavailable.
 * Used in the server interruption alternative flow.
 */
public class ServiceUnavailableException extends Exception {
    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
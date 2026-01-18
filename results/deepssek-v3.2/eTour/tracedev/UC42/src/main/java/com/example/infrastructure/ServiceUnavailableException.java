package com.example.infrastructure;

/**
 * Exception thrown when a service (e.g., database) is unavailable.
 */
public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
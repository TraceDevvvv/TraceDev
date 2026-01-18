package com.example.search.application;

/**
 * Custom exception for service layer to wrap infrastructure exceptions.
 * This provides a layer of abstraction and prevents infrastructure-specific
 * exceptions from leaking into the higher layers directly.
 */
public class ServiceConnectionException extends RuntimeException {
    public ServiceConnectionException(String message) {
        super(message);
    }

    public ServiceConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
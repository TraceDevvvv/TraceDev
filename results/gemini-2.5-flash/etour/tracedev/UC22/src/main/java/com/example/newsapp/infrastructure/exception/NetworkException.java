package com.example.newsapp.infrastructure.exception;

/**
 * Custom exception to simulate network interruption.
 * Supports REQ-EX-3.
 */
public class NetworkException extends Exception {
    /**
     * Constructs a new NetworkException with the specified detail message.
     * @param message The detail message.
     */
    public NetworkException(String message) {
        super(message);
    }
}
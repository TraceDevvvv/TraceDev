package com.example.domain;

/**
 * Custom exception for network connectivity issues.
 * Added to satisfy R19.
 */
public class NetworkConnectionException extends RuntimeException {
    private String message;

    public NetworkConnectionException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
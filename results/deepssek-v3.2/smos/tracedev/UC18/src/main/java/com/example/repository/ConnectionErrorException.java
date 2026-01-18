package com.example.repository;

/**
 * Custom exception for SMOS connection failures.
 */
public class ConnectionErrorException extends RuntimeException {
    public ConnectionErrorException(String message) {
        super(message);
    }

    public ConnectionErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
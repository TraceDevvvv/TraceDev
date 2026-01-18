package com.example.turista.application;

/**
 * Custom exception for when a Turista is not found by the application layer.
 * This typically wraps lower-level data access exceptions for client consumption.
 */
public class TuristaNotFoundException extends Exception {
    public TuristaNotFoundException(String message) {
        super(message);
    }

    public TuristaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
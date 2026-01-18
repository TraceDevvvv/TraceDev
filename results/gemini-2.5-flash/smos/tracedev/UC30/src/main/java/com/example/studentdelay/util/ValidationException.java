package com.example.studentdelay.util;

/**
 * Custom exception for validation failures in the application layer.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
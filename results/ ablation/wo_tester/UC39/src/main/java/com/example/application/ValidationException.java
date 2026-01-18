package com.example.application;

/**
 * Exception thrown when image validation fails.
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
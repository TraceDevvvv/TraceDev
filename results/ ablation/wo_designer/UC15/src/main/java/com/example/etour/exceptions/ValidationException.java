package com.example.etour.exceptions;

/**
 * Exception thrown when validation of tourist data fails.
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
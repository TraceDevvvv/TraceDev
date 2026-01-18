package com.example.exception;

/**
 * Custom exception for validation failures,
 * indicating that input data does not meet required criteria.
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
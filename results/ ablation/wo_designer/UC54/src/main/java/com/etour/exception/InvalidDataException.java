package com.etour.exception;

/**
 * Exception thrown when entered data is invalid or insufficient.
 * Corresponds to the Errored use case trigger.
 */
public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}
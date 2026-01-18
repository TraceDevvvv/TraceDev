package com.etour.registration.exception;

/**
 * Base exception for persistence-related errors.
 */
public class PersistenceException extends RuntimeException {
    public PersistenceException(String message) {
        super(message);
    }
}
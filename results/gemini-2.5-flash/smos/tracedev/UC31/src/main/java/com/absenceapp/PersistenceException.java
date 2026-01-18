package com.absenceapp;

/**
 * Custom exception for persistence-related errors.
 * REQ-002: Extends RuntimeException as per the diagram.
 */
public class PersistenceException extends RuntimeException {
    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
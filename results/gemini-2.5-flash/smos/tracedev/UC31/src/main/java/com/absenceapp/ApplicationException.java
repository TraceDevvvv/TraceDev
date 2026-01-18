package com.absenceapp;

/**
 * Custom exception for application-level errors.
 * REQ-002: Extends RuntimeException as it is often used for unchecked business logic errors.
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
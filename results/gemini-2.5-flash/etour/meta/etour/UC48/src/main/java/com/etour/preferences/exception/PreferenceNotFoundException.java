package com.etour.preferences.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when a requested user preference is not found.
 * This exception maps to an HTTP 404 Not Found status code.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PreferenceNotFoundException extends RuntimeException {

    /**
     * Constructs a new PreferenceNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public PreferenceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new PreferenceNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public PreferenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
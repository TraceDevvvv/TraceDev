package com.etour.preferences.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when provided preference data is invalid.
 * This exception maps to an HTTP 400 Bad Request status code.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPreferenceDataException extends RuntimeException {

    /**
     * Constructs a new InvalidPreferenceDataException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidPreferenceDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidPreferenceDataException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidPreferenceDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
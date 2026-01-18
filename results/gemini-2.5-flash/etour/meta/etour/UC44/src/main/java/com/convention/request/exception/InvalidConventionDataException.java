package com.convention.request.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when convention data provided by the user is invalid or insufficient.
 * This exception maps to an HTTP 400 Bad Request status.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidConventionDataException extends RuntimeException {

    /**
     * Constructs a new InvalidConventionDataException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidConventionDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidConventionDataException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidConventionDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
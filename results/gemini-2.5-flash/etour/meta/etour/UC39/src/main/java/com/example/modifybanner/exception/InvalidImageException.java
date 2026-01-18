package com.example.modifybanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to indicate that an uploaded image file is invalid.
 * This could be due to size, format, or dimension constraints.
 * Annotated with @ResponseStatus to automatically return a 400 Bad Request HTTP status.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidImageException extends RuntimeException {

    /**
     * Constructs a new InvalidImageException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidImageException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidImageException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
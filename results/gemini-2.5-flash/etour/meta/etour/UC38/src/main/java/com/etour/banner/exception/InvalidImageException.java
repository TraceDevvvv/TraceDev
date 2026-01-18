package com.etour.banner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for invalid image uploads.
 * This exception is thrown when an uploaded image does not meet the specified criteria
 * (e.g., wrong file type, excessive size, invalid dimensions).
 * It maps to an HTTP 400 Bad Request status.
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
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
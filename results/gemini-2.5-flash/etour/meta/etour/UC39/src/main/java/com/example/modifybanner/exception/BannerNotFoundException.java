package com.example.modifybanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to indicate that a requested banner was not found.
 * Annotated with @ResponseStatus to automatically return a 404 Not Found HTTP status.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BannerNotFoundException extends RuntimeException {

    /**
     * Constructs a new BannerNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public BannerNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new BannerNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public BannerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
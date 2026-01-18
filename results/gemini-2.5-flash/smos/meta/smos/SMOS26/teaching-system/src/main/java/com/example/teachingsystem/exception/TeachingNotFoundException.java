package com.example.teachingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when a requested Teaching entity is not found.
 * This exception is typically mapped to an HTTP 404 Not Found status.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // Maps this exception to an HTTP 404 status code
public class TeachingNotFoundException extends RuntimeException {

    /**
     * Constructs a new TeachingNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public TeachingNotFoundException(String message) {
        super(message);
    }
}
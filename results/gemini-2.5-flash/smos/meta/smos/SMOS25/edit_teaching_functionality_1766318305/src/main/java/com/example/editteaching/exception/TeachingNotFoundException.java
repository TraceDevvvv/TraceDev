package com.example.editteaching.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when a requested Teaching entity is not found.
 * This exception is mapped to an HTTP 404 Not Found status code by Spring's
 * {@link ResponseStatus} annotation, which is handled by the {@link GlobalExceptionHandler}.
 * It strictly adheres to the data structures and interfaces defined in the system design.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // Maps this exception to an HTTP 404 status code
public class TeachingNotFoundException extends RuntimeException {

    /**
     * Constructs a new TeachingNotFoundException with the specified detail message.
     * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     *
     * @param message The detail message (which is saved for later retrieval).
     */
    public TeachingNotFoundException(String message) {
        super(message);
    }
}
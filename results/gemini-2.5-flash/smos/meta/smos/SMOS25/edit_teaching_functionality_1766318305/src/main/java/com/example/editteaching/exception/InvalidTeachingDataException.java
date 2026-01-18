package com.example.editteaching.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when teaching data provided is invalid.
 * This exception is typically thrown by the service layer when business rules
 * or custom validation logic determines that the data is not acceptable.
 * It is mapped to an HTTP 400 Bad Request status code by Spring's
 * {@link ResponseStatus} annotation, which is handled by the {@link GlobalExceptionHandler}.
 * This corresponds to the "Errodati" use case mentioned in the requirements.
 * It strictly adheres to the data structures and interfaces defined in the system design.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST) // Maps this exception to an HTTP 400 status code
public class InvalidTeachingDataException extends RuntimeException {

    /**
     * Constructs a new InvalidTeachingDataException with the specified detail message.
     * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     *
     * @param message The detail message (which is saved for later retrieval).
     */
    public InvalidTeachingDataException(String message) {
        super(message);
    }
}
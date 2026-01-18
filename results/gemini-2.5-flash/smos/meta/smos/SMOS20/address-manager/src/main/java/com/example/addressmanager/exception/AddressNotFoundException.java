package com.example.addressmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when an {@link com.example.addressmanager.domain.Address}
 * with a given ID is not found in the system.
 * This exception is typically mapped to an HTTP 404 Not Found status code
 * when handled by a Spring MVC controller.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // Maps this exception to an HTTP 404 status code
public class AddressNotFoundException extends RuntimeException {

    /**
     * Constructs a new AddressNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public AddressNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new AddressNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     */
    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
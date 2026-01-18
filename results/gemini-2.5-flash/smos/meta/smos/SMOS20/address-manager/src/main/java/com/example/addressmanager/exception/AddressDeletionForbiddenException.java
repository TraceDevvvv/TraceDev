package com.example.addressmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when an attempt to delete an {@link com.example.addressmanager.domain.Address}
 * is forbidden, typically because it has associated entities (e.g., AssociatedClasses).
 * This exception is typically mapped to an HTTP 409 Conflict status code
 * when handled by a Spring MVC controller.
 */
@ResponseStatus(HttpStatus.CONFLICT) // Maps this exception to an HTTP 409 Conflict status code
public class AddressDeletionForbiddenException extends RuntimeException {

    /**
     * Constructs a new AddressDeletionForbiddenException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public AddressDeletionForbiddenException(String message) {
        super(message);
    }

    /**
     * Constructs a new AddressDeletionForbiddenException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     */
    public AddressDeletionForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
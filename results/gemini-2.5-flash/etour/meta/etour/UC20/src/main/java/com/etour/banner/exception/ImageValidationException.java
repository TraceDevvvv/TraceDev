package com.etour.banner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception indicating that an uploaded image failed validation checks.
 * This could be due to invalid file type, size, dimensions, or other characteristics.
 * This exception maps to an HTTP 400 Bad Request status.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImageValidationException extends RuntimeException {

    /**
     * Constructs a new ImageValidationException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ImageValidationException(String message) {
        super(message);
    }
}
package com.etour.banner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for exceeding the maximum number of banners allowed
 * for a specific Point of Rest.
 * This exception is thrown when an operator attempts to insert a banner
 * but the limit for that Point of Rest has already been reached.
 * It maps to an HTTP 400 Bad Request status.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxBannersExceededException extends RuntimeException {

    /**
     * Constructs a new MaxBannersExceededException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public MaxBannersExceededException(String message) {
        super(message);
    }

    /**
     * Constructs a new MaxBannersExceededException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public MaxBannersExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
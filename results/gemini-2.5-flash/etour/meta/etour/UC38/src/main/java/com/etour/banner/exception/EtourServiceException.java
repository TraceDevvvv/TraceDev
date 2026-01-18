package com.etour.banner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for ETOUR service communication errors.
 * This exception is thrown when there is an issue interacting with the
 * external ETOUR server, such as connection interruption or an error
 * returned by the ETOUR service.
 * It maps to an HTTP 500 Internal Server Error status.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EtourServiceException extends RuntimeException {

    /**
     * Constructs a new EtourServiceException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public EtourServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new EtourServiceException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public EtourServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
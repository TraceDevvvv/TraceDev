package com.convention.request.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when there is an issue communicating with the external agency system (ETOUR).
 * This exception typically maps to an HTTP 503 Service Unavailable status, indicating a temporary
 * external service issue.
 */
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class AgencyCommunicationException extends RuntimeException {

    /**
     * Constructs a new AgencyCommunicationException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public AgencyCommunicationException(String message) {
        super(message);
    }

    /**
     * Constructs a new AgencyCommunicationException with the specified detail message and cause.
     * This is useful for wrapping lower-level exceptions (e.g., network errors, HTTP client exceptions).
     *
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public AgencyCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
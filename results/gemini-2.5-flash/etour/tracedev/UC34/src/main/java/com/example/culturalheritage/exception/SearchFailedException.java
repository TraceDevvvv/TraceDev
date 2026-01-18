package com.example.culturalheritage.exception;

/**
 * Custom exception to indicate a failure during the search operation,
 * typically due to issues with external system connectivity or data retrieval.
 */
public class SearchFailedException extends Exception {

    /**
     * Constructs a new SearchFailedException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public SearchFailedException(String message) {
        super(message);
    }

    /**
     * Constructs a new SearchFailedException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     */
    public SearchFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
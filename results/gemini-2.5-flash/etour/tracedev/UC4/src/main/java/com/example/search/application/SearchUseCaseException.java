package com.example.search.application;

/**
 * Custom exception for use case layer errors in the search flow.
 * This provides a clean way for the Presentation Layer to catch and handle
 * any business logic or underlying technical errors during a search operation.
 */
public class SearchUseCaseException extends RuntimeException {
    public SearchUseCaseException(String message) {
        super(message);
    }

    public SearchUseCaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
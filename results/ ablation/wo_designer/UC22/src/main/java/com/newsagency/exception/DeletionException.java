package com.newsagency.exception;

/**
 * Custom exception for deletion-related errors.
 */
public class DeletionException extends Exception {
    public DeletionException(String message) {
        super(message);
    }

    public DeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
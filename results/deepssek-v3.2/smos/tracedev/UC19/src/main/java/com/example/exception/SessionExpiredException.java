package com.example.exception;

/**
 * Exception thrown when a user session has expired.
 */
public class SessionExpiredException extends Exception {
    public SessionExpiredException(String message) {
        super(message);
    }
}
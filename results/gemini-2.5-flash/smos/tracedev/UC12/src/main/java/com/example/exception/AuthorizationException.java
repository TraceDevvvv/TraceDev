package com.example.exception;

/**
 * Custom exception for authorization failures,
 * indicating that a user does not have the necessary permissions.
 */
public class AuthorizationException extends Exception {
    public AuthorizationException(String message) {
        super(message);
    }
}
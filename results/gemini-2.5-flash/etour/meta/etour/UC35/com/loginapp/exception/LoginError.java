package com.loginapp.exception;

/**
 * Custom exception class for handling login-related errors.
 * This allows for specific error messages to be propagated when authentication fails.
 */
public class LoginError extends Exception {

    /**
     * Constructs a new LoginError with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public LoginError(String message) {
        super(message);
    }

    /**
     * Constructs a new LoginError with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public LoginError(String message, Throwable cause) {
        super(message, cause);
    }
}
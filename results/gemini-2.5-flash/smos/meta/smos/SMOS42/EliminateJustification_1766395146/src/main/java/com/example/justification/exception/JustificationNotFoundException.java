package com.example.justification.exception;

/**
 * Custom exception to be thrown when a requested Justification cannot be found in the system.
 * This is a runtime exception, meaning it does not need to be explicitly caught or declared
 * in method signatures, simplifying API usage for common "not found" scenarios.
 */
public class JustificationNotFoundException extends RuntimeException {

    /**
     * Constructs a new JustificationNotFoundException with no detail message.
     */
    public JustificationNotFoundException() {
        super();
    }

    /**
     * Constructs a new JustificationNotFoundException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public JustificationNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new JustificationNotFoundException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     *              (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public JustificationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new JustificationNotFoundException with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than wrappers for other throwables.
     *
     * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     *              (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public JustificationNotFoundException(Throwable cause) {
        super(cause);
    }
}
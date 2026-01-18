package com.example.justification.exception;

/**
 * Custom exception to be thrown when there is an issue connecting to or communicating with the SMOS server.
 * This is a runtime exception, indicating a potentially unrecoverable or critical system error
 * related to external service dependency.
 */
public class SMOSServerException extends RuntimeException {

    /**
     * Constructs a new SMOSServerException with no detail message.
     */
    public SMOSServerException() {
        super();
    }

    /**
     * Constructs a new SMOSServerException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     */
    public SMOSServerException(String message) {
        super(message);
    }

    /**
     * Constructs a new SMOSServerException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link Throwable#getMessage()} method).
     * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     *              (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SMOSServerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new SMOSServerException with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
     * This constructor is useful for exceptions that are little more than wrappers for other throwables.
     *
     * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
     *              (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public SMOSServerException(Throwable cause) {
        super(cause);
    }
}
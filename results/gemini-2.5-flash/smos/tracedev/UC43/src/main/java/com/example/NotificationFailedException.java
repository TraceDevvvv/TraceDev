package com.example;

/**
 * Custom exception indicating that a notification sending operation failed.
 */
public class NotificationFailedException extends Exception {

    /**
     * Constructs a new NotificationFailedException with the specified detail message.
     *
     * @param message The detail message.
     */
    public NotificationFailedException(String message) {
        super(message);
    }

    /**
     * Constructs a new NotificationFailedException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public NotificationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
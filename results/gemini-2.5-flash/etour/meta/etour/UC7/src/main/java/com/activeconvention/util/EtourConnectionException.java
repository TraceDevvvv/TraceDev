package com.activeconvention.util;

/**
 * Custom exception for ETOUR server connection issues.
 * This exception is thrown when there is an interruption or failure
 * in connecting to the ETOUR server during convention activation.
 */
public class EtourConnectionException extends Exception {

    /**
     * Constructs a new EtourConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public EtourConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new EtourConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public EtourConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.example.smos.exception;

/**
 * Custom exception to indicate issues with connecting to the SMOS server.
 * This satisfies requirement REQ-008.
 */
public class SMOSServerConnectionException extends Exception {

    /**
     * Constructs a new SMOSServerConnectionException with no detail message.
     */
    public SMOSServerConnectionException() {
        super();
    }

    /**
     * Constructs a new SMOSServerConnectionException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the Throwable.getMessage() method.
     */
    public SMOSServerConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new SMOSServerConnectionException with the specified detail message and
     * cause.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                Throwable.getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the
     *              Throwable.getCause() method). (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public SMOSServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new SMOSServerConnectionException with the specified cause and a detail
     * message of (cause==null ? null : cause.toString()) (which typically
     * contains the class and detail message of cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              Throwable.getCause() method). (A null value is permitted,
     *              and indicates that the cause is nonexistent or unknown.)
     */
    public SMOSServerConnectionException(Throwable cause) {
        super(cause);
    }
}
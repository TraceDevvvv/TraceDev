package com.example.attendancetracker.exception;

/**
 * Custom exception to represent failures when interacting with external serv.
 */
public class ExternalServiceException extends Exception {
    public ExternalServiceException(String message) {
        super(message);
    }

    public ExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
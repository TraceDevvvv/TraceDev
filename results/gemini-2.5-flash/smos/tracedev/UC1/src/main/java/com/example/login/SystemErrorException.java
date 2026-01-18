package com.example.login;

/**
 * Exception Class: Thrown when a system-level error occurs during the login process,
 * such as a connection issue to an external system.
 * // Added to satisfy requirement R13, R14
 */
public class SystemErrorException extends Exception {
    public SystemErrorException(String message) {
        super(message);
    }
}
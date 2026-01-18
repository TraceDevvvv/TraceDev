package com.example.login;

/**
 * Exception Class: Thrown when login credentials (username/password) are invalid.
 * // Added to satisfy requirement R8, R9, R10
 */
public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
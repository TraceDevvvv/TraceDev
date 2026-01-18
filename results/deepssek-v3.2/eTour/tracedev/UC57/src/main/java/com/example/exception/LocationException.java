package com.example.exception;

/**
 * Exception thrown when location cannot be obtained.
 */
public class LocationException extends Exception {
    public LocationException(String message) {
        super(message);
    }
}
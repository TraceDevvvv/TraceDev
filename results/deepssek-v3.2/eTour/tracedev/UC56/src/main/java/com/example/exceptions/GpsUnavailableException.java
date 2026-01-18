package com.example.exceptions;

/**
 * Exception thrown when GPS hardware is unavailable or times out.
 */
public class GpsUnavailableException extends RuntimeException {
    public GpsUnavailableException(String message) {
        super(message);
    }
}
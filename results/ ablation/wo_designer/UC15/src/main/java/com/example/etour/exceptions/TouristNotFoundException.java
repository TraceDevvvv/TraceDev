package com.example.etour.exceptions;

/**
 * Exception thrown when a tourist is not found.
 */
public class TouristNotFoundException extends Exception {
    public TouristNotFoundException(String message) {
        super(message);
    }
}
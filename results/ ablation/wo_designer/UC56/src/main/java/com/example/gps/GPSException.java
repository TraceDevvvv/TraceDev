package com.example.gps;

/**
 * Exception thrown when the GPS system cannot determine the position.
 */
public class GPSException extends Exception {
    public GPSException(String message) {
        super(message);
    }
}
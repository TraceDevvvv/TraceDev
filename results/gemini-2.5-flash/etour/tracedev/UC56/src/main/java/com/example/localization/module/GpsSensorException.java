package com.example.localization.module;

/**
 * Custom exception to indicate issues with the GPS sensor.
 */
public class GpsSensorException extends Exception {
    public GpsSensorException(String message) {
        super(message);
    }

    public GpsSensorException(String message, Throwable cause) {
        super(message, cause);
    }
}
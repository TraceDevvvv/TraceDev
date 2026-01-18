package com.etour.exception;

/**
 * Exception thrown when image validation fails.
 */
public class ImageValidationException extends Exception {
    public ImageValidationException(String message) {
        super(message);
    }
}
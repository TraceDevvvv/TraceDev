package com.restaurant.menu.exception;

/**
 * Exception thrown when service is unavailable (requirement R16).
 */
public class ServiceUnavailableException extends RuntimeException {
    private String message;

    public ServiceUnavailableException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
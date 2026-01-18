package com.etour.exception;

/**
 * Models resource not found errors
 */
public class NotFoundException extends RuntimeException {
    private String message;
    private String resourceId;

    public NotFoundException(String message, String resourceId) {
        super(message + " (Resource ID: " + resourceId + ")");
        this.message = message;
        this.resourceId = resourceId;
    }

    public String getMessage() {
        return message;
    }

    public String getResourceId() {
        return resourceId;
    }
}
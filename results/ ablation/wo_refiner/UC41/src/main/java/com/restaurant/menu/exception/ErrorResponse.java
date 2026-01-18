package com.restaurant.menu.exception;

/**
 * Represents an error response (requirement R16).
 */
public class ErrorResponse extends RuntimeException {
    private int statusCode;
    private String message;

    public ErrorResponse(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
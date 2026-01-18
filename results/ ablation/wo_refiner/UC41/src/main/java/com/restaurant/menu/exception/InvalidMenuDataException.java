package com.restaurant.menu.exception;

import java.util.List;

/**
 * Exception for invalid menu data (requirement R11).
 */
public class InvalidMenuDataException extends RuntimeException {
    private String message;
    private List<String> validationErrors;

    public InvalidMenuDataException(String message, List<String> errors) {
        super(message);
        this.message = message;
        this.validationErrors = errors;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}
package com.example;

import java.util.List;

/**
 * Response object for edit user operation.
 */
public class EditUserResponse {
    private boolean success;
    private String message;
    private List<String> errors;

    private EditUserResponse(boolean success, String message, List<String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors;
    }

    public static EditUserResponse success(String message) {
        return new EditUserResponse(true, message, java.util.Collections.emptyList());
    }

    public static EditUserResponse failure(String message, List<String> errors) {
        return new EditUserResponse(false, message, errors);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
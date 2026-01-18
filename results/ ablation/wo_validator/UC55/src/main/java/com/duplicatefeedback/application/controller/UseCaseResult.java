package com.duplicatefeedback.application.controller;

/**
 * Represents the result of a use case execution.
 */
public class UseCaseResult {
    private final boolean success;
    private final String message;
    private final Object data;

    public UseCaseResult(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
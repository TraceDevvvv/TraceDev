package com.example.usecase;

/**
 * Represents a generic operation result.
 * Maps to the Result class in the UML diagram.
 */
public class Result {
    private boolean success;
    private String message;
    private Object data;

    public Result(boolean success, String message, Object data) {
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
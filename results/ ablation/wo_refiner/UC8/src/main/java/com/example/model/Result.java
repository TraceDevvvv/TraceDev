package com.example.model;

/**
 * Generic result container.
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

    public static Result success(String message, Object data) {
        return new Result(true, message, data);
    }

    public static Result failure(String message) {
        return new Result(false, message, null);
    }
}
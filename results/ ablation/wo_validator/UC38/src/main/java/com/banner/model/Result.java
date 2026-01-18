package com.banner.model;

/**
 * Result of an operation.
 */
public class Result {
    private boolean success;
    private String message;
    private String bannerId;

    public Result(boolean success, String message, String bannerId) {
        this.success = success;
        this.message = message;
        this.bannerId = bannerId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getBannerId() {
        return bannerId;
    }
}
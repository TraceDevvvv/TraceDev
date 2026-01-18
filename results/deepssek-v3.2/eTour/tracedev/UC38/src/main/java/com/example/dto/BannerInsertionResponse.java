package com.example.dto;

/**
 * Data Transfer Object for banner insertion responses.
 */
public class BannerInsertionResponse {
    private boolean success;
    private String message;
    private String bannerId;
    private String errorCode;

    public BannerInsertionResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public BannerInsertionResponse(boolean success, String message, String bannerId, String errorCode) {
        this.success = success;
        this.message = message;
        this.bannerId = bannerId;
        this.errorCode = errorCode;
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

    public String getErrorCode() {
        return errorCode;
    }
}
package com.banner.dto;

/**
 * Response DTO for banner insertion operation.
 */
public class InsertBannerResponse {
    private boolean success;
    private String bannerId;
    private String message;

    public InsertBannerResponse(boolean success, String bannerId, String message) {
        this.success = success;
        this.bannerId = bannerId;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getMessage() {
        return message;
    }
}
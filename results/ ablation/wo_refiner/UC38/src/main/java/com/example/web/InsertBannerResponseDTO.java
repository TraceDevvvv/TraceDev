package com.example.web;

/**
 * Data Transfer Object for the insertion response.
 */
public class InsertBannerResponseDTO {
    private boolean success;
    private String message;
    private String bannerId;

    public InsertBannerResponseDTO(boolean success, String message, String bannerId) {
        this.success = success;
        this.message = message;
        this.bannerId = bannerId;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getBannerId() {
        return bannerId;
    }
}
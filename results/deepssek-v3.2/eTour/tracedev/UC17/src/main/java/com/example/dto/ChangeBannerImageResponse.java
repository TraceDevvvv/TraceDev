package com.example.dto;

/**
 * DTO for change banner image response.
 */
public class ChangeBannerImageResponse implements DataTransferObject {
    private boolean success;
    private String message;
    private String updatedBannerId;

    public ChangeBannerImageResponse(boolean success, String message, String updatedBannerId) {
        this.success = success;
        this.message = message;
        this.updatedBannerId = updatedBannerId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getUpdatedBannerId() {
        return updatedBannerId;
    }
}
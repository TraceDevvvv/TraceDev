package com.example.dto;

/**
 * Data Transfer Object for deletion confirmation response.
 * Modified return type from audit report REQ-009.
 */
public class DeletionConfirmationDTO {
    private int newsId;
    private boolean success;
    private String message;

    public DeletionConfirmationDTO(int newsId, boolean success, String message) {
        this.newsId = newsId;
        this.success = success;
        this.message = message;
    }

    public int getNewsId() {
        return newsId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
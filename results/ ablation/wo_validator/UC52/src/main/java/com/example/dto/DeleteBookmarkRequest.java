package com.example.dto;

/**
 * Request object for delete bookmark operation.
 */
public class DeleteBookmarkRequest {
    private String userId;
    private String siteId;
    private String confirmationToken;

    public DeleteBookmarkRequest(String userId, String siteId, String confirmationToken) {
        this.userId = userId;
        this.siteId = siteId;
        this.confirmationToken = confirmationToken;
    }

    public String getUserId() {
        return userId;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }
}
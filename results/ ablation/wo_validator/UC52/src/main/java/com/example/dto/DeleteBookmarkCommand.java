package com.example.dto;

/**
 * Command object for delete bookmark operation.
 */
public class DeleteBookmarkCommand {
    private String userId;
    private String siteId;
    private String confirmationToken;

    public DeleteBookmarkCommand(String userId, String siteId, String confirmationToken) {
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
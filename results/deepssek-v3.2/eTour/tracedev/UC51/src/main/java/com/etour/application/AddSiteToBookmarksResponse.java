package com.etour.application;

/**
 * Response DTO for the AddSiteToBookmarks use case.
 */
public class AddSiteToBookmarksResponse {
    private final boolean success;
    private final String bookmarkId;
    private final String message;

    public AddSiteToBookmarksResponse(boolean success, String bookmarkId, String message) {
        this.success = success;
        this.bookmarkId = bookmarkId;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public String getMessage() {
        return message;
    }
}
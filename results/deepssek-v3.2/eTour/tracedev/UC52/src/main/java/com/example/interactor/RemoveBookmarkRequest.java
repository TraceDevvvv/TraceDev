package com.example.interactor;

/**
 * Request object for removing a bookmark.
 */
public class RemoveBookmarkRequest {
    private String touristId;
    private String siteId;

    public RemoveBookmarkRequest(String touristId, String siteId) {
        this.touristId = touristId;
        this.siteId = siteId;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSiteId() {
        return siteId;
    }
}
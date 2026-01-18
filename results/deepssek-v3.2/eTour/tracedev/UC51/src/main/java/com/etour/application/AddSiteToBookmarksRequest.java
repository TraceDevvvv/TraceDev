package com.etour.application;

/**
 * Request DTO for the AddSiteToBookmarks use case.
 */
public class AddSiteToBookmarksRequest {
    private final String touristId;
    private final String siteId;

    public AddSiteToBookmarksRequest(String touristId, String siteId) {
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
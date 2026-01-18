package com.example.usecase.command;

/**
 * Command: Request model for inserting a site
 */
public class InsertSiteCommand {
    private String touristId;
    private String siteId;

    public InsertSiteCommand(String touristId, String siteId) {
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
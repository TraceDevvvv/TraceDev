package com.etour.model;

/**
 * Holds site details.
 * Added to satisfy requirement Flow of Events (1, 3)
 */
public class SiteInfo {
    private String siteId;
    private String name;
    private String location;

    public SiteInfo(String siteId, String name, String location) {
        this.siteId = siteId;
        this.name = name;
        this.location = location;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
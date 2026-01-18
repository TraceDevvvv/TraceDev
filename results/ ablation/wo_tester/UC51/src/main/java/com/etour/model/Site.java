package com.etour.model;

/**
 * Represents a tourist site.
 */
public class Site {
    private String siteId;
    private String name;
    private String location;

    public Site(String siteId, String name, String location) {
        this.siteId = siteId;
        this.name = name;
        this.location = location;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Added to satisfy requirement Flow of Events (1, 3)
     * Returns site details for the given site ID.
     * @param siteId the site ID
     * @return SiteInfo object with details
     */
    public SiteInfo getSiteDetails(String siteId) {
        // In a real scenario, this would fetch from a database.
        // Here we return a dummy SiteInfo.
        return new SiteInfo(siteId, this.name, this.location);
    }
}

package com.example.model;

/**
 * Represents a Site in the system.
 */

public class Site {
    // Attributes
    private String siteId;
    private String siteName;
    private String location;

    /**
     * Constructs a new Site instance.
     * @param siteId A unique identifier for the site.
     * @param siteName The name of the site.
     * @param location The geographical location of the site.
     */
    public Site(String siteId, String siteName, String location) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.location = location;
    }

    // Getters for attributes
    public String getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getLocation() {
        return location;
    }

    /**
     * Provides a descriptive string for the site.
     * @return A string containing site name and location.
     */
    public String getDescription() {
        return "Site: " + siteName + " at " + location;
    }

    @Override
    public String toString() {
        return "Site{" +
               "siteId='" + siteId + '\'' +
               ", siteName='" + siteName + '\'' +
               ", location='" + location + '\'' +
               '}';
    }
}

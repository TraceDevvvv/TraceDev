package com.etoour.model;

import java.util.List;

/**
 * Site class representing a tourist site.
 */
public class Site {
    private String siteId;
    private String name;
    private String location;
    private String description;

    public Site(String siteId, String name, String location, String description) {
        this.siteId = siteId;
        this.name = name;
        this.location = location;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get feedbacks for this site.
     * Note: Placeholder implementation.
     */
    public List<Feedback> getFeedbacks() {
        // In a real implementation, would fetch from a repository
        return null;
    }
}
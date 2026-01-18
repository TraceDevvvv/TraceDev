package com.example.model;

/**
 * Represents a tourist site.
 */
public class Site {
    private String siteId;
    private String name;
    private String description;
    private SiteFeatures features;

    public Site(String siteId, String name, String description, SiteFeatures features) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.features = features;
    }

    public String getSiteId() {
        return siteId;
    }

    public SiteFeatures getFeatures() {
        return features;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Site{id=" + siteId + ", name=" + name + "}";
    }
}
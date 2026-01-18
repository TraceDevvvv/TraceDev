package com.example.domain;

/**
 * Represents a tourist site.
 * This is a core domain entity.
 */
public class Site {
    public String siteId;
    public String name;
    public String description;

    // Constructor
    public Site(String siteId, String name, String description) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
    }

    // Getters for all attributes
    public String getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Site{" +
               "siteId='" + siteId + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
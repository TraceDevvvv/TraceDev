package com.example.model;

/**
 * Represents a Site entity with details.
 */
public class Site {
    private String siteId;
    private String name;
    private String description;

    public Site(String siteId, String name, String description) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
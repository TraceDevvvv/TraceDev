package com.example.etour.model;

/**
 * Represents a tourist site with a unique identifier and name.
 */
public class Site {
    private final String siteId;
    private final String name;

    public Site(String siteId, String name) {
        this.siteId = siteId;
        this.name = name;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteId='" + siteId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
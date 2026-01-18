package com.example;

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

    public String getSiteDetails() {
        return "Site ID: " + siteId + ", Name: " + name + ", Location: " + location;
    }
}
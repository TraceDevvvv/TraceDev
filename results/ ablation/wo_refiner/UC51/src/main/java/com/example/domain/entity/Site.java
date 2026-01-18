package com.example.domain.entity;

/**
 * Core Domain Entity: Site
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

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
package com.example.model;

import java.util.Date;

/**
 * Represents a Site with an ID, name, and location.
 */
public class Site {
    private int siteId;
    private String name;
    private String location;

    public Site(int siteId, String name, String location) {
        this.siteId = siteId;
        this.name = name;
        this.location = location;
    }

    public int getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
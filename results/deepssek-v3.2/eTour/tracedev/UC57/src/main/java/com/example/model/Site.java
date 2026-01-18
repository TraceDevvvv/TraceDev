package com.example.model;

import java.util.Map;

/**
 * Represents a tourist site with an ID, name, and location.
 */
public class Site {
    private String id;
    private String name;
    private Location location;

    public Site(Map<String, Object> siteData) {
        this.id = (String) siteData.get("id");
        this.name = (String) siteData.get("name");
        double lat = (double) siteData.get("latitude");
        double lon = (double) siteData.get("longitude");
        this.location = new Location(lat, lon);
    }

    public Site(String id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
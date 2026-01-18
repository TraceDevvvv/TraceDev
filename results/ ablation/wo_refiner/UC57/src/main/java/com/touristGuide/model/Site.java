package com.touristGuide.model;

import java.util.Map;

public class Site {
    private String id;
    private String name;
    private String description;
    private Location location;
    private Map<String, Object> attributes;

    public Site(String id, String name, String description, Location location, Map<String, Object> attributes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
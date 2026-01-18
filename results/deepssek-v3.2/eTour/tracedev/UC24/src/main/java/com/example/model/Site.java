package com.example.model;

/**
 * Entity class representing a site.
 */
public class Site {
    private String id;
    private String name;
    private String location;

    public Site(String id, String name, String location) {
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

    public String getLocation() {
        return location;
    }
}
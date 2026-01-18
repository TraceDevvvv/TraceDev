package com.example.model;

/**
 * Entity representing a point of rest.
 * Contains a flag to indicate if it is designated.
 */
public class PointOfRest {
    private String id;
    private String name;
    private String location;
    private boolean isDesignated;

    public PointOfRest(String id, String name, String location, boolean isDesignated) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.isDesignated = isDesignated;
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

    public boolean isDesignated() {
        return isDesignated;
    }
}
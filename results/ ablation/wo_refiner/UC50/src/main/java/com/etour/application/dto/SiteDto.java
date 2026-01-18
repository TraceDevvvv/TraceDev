package com.etour.application.dto;

/**
 * Data Transfer Object for site information.
 */
public class SiteDto {
    private String id;
    private String name;
    private String location;

    public SiteDto(String id, String name, String location) {
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

    // For transformation and display purposes
    @Override
    public String toString() {
        return "SiteDto{id='" + id + "', name='" + name + "', location='" + location + "'}";
    }
}
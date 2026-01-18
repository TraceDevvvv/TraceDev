package com.example.dto;

/**
 * Data Transfer Object for site information.
 * Used by the UI to display site list (from SearchSite use case).
 */
public class SiteDTO {
    private String id;
    private String name;
    private String location;

    public SiteDTO(String id, String name, String location) {
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
package com.example;

/**
 * Represents a site with id, name, location, and details.
 */
public class Site {
    private Long id;
    private String name;
    private String location;
    private String details;

    public Site() {
    }

    public Site(Long id, String name, String location, String details) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
package com.example.etour.model;

/**
 * Represents a refreshment point (turning point) that can have banners.
 */
public class RefreshmentPoint {
    private int id;
    private String name;
    private String location;

    public RefreshmentPoint() {}

    public RefreshmentPoint(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return "RefreshmentPoint [id=" + id + ", name=" + name + ", location=" + location + "]";
    }
}
package com.example.domain;

/**
 * Domain entity representing a refreshment point.
 */
public class RefreshmentPoint {
    private int id;
    private String name;
    private String location;
    private String type;

    public RefreshmentPoint() {}

    public RefreshmentPoint(int id, String name, String location, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{id=" + id + ", name='" + name + "', location='" + location + "', type='" + type + "'}";
    }
}
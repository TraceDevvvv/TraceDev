package com.example.model;

import java.util.List;
import java.util.Objects;

/**
 * Represents a point of rest with its attributes.
 */
public class PointOfRest {
    private String id;
    private String name;
    private String location;
    private List<String> facilities;

    public PointOfRest() {
        // Default constructor
    }

    public PointOfRest(String id, String name, String location, List<String> facilities) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.facilities = facilities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<String> facilities) {
        this.facilities = facilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointOfRest that = (PointOfRest) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(location, that.location) && Objects.equals(facilities, that.facilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, facilities);
    }

    @Override
    public String toString() {
        return "PointOfRest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", facilities=" + facilities +
                '}';
    }
}
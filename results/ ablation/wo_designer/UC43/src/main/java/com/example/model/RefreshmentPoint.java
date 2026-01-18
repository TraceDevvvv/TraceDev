// Entity class representing a refreshment point
package com.example.model;

import java.util.Objects;

public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;
    private String description;
    private int seatingCapacity;
    private String operatingHours;

    // Default constructor
    public RefreshmentPoint() {
    }

    // Parameterized constructor
    public RefreshmentPoint(String id, String name, String location, String description, int seatingCapacity, String operatingHours) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.seatingCapacity = seatingCapacity;
        this.operatingHours = operatingHours;
    }

    // Getters and setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshmentPoint that = (RefreshmentPoint) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", operatingHours='" + operatingHours + '\'' +
                '}';
    }
}
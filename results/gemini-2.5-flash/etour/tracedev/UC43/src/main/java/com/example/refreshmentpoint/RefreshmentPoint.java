package com.example.refreshmentpoint;

/**
 * Represents a Refreshment Point entity.
 * Contains attributes and methods related to the core data of a refreshment point.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;
    private String type;
    // Add other relevant attributes of a refreshment point as needed

    public RefreshmentPoint(String id, String name, String location, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    // Setters (if needed, but updateDetails is provided for business logic)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Updates the details of the refreshment point.
     *
     * @param name The new name for the refreshment point.
     * @param location The new location for the refreshment point.
     * @param type The new type for the refreshment point.
     */
    public void updateDetails(String name, String location, String type) {
        this.name = name;
        this.location = location;
        this.type = type;
        System.out.println("RefreshmentPoint " + this.id + " updated details: Name=" + name + ", Location=" + location + ", Type=" + type);
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
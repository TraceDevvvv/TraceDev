package com.example.pointofrest;

/**
 * Represents a Point of Rest entity in the system.
 * This class holds the detailed information about a specific point of rest.
 */
public class PointOfRest {
    public String id;
    public String name;
    public String address;
    public String type;
    public String description;
    public String contactInfo;

    public PointOfRest() {
        // Default constructor
    }

    public PointOfRest(String id, String name, String address, String type, String description, String contactInfo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
        this.description = description;
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "PointOfRest{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", type='" + type + '\'' +
               ", description='" + description + '\'' +
               ", contactInfo='" + contactInfo + '\'' +
               '}';
    }
}
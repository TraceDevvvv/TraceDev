package com.example.models;

import java.io.Serializable;

/**
 * Represents a Refreshment Point (Point of Rest)
 */
public class RefreshmentPoint implements Serializable {
    private String id;
    private String name;
    private String address;
    private String contactNumber;
    private String operationalHours;
    private int capacity;
    private String description;

    public RefreshmentPoint(String id, String name, String address, String contactNumber, 
                            String operationalHours, int capacity, String description) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
        this.operationalHours = operationalHours;
        this.capacity = capacity;
        this.description = description;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContactNumber() { return contactNumber; }
    public String getOperationalHours() { return operationalHours; }
    public int getCapacity() { return capacity; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", operationalHours='" + operationalHours + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                '}';
    }
}
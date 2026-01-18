package com.example.model;

import java.util.List;

/**
 * Domain entity representing a Point of Rest.
 */
public class PointOfRest {
    private String id;
    private String name;
    private String address;
    private List<String> facilities;

    public PointOfRest() {}

    public PointOfRest(String id, String name, String address, List<String> facilities) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.facilities = facilities;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<String> getFacilities() { return facilities; }
    public void setFacilities(List<String> facilities) { this.facilities = facilities; }
}
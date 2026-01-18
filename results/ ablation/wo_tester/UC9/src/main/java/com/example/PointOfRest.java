package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Domain entity representing a Point of Rest.
 */
public class PointOfRest {
    public String id;
    public String name;
    public String location;
    public List<String> amenities;

    public PointOfRest(String id, String name, String location, List<String> amenities) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.amenities = amenities;
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

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    /**
     * Returns a map representation of the point's details.
     * @return map of details
     */
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("id", id);
        details.put("name", name);
        details.put("location", location);
        details.put("amenities", amenities);
        return details;
    }
}
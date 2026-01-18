package com.etour.model;

import com.etour.model.RefreshmentPointDetails;

/**
 * Represents a refreshment point
 */
public class RefreshmentPoint {
    private String pointId;
    public String name;
    public String address;
    public String type;

    public RefreshmentPoint(String pointId, String name, String address, String type) {
        this.pointId = pointId;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RefreshmentPointDetails getDetails() {
        // Creates a details object based on basic attributes, with default values for extra fields
        return new RefreshmentPointDetails(pointId, name, address, type, "Unknown", 0, null, "9:00-18:00");
    }
}
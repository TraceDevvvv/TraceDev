package com.example.dto;

/**
 * Data Transfer Object for RefreshmentPoint.
 */
public class RefreshmentPointDTO {
    private String pointId;
    private String location;
    private String description;

    public RefreshmentPointDTO() {
    }

    public RefreshmentPointDTO(String pointId, String location, String description) {
        this.pointId = pointId;
        this.location = location;
        this.description = description;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
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
}
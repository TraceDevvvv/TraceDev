package com.example.dto;

import com.example.domain.RefreshmentPoint;

/**
 * Data Transfer Object for RefreshmentPoint, used by the presentation layer.
 */
public class RefreshmentPointDTO {
    private String id;
    private String name;
    private String location;
    private String description;

    public RefreshmentPointDTO(RefreshmentPoint point) {
        this.id = point.getId();
        this.name = point.getName();
        this.location = point.getLocation();
        this.description = point.getDescription();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}
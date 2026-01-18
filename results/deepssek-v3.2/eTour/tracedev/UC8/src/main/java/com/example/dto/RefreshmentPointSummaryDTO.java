package com.example.dto;

import com.example.domain.RefreshmentPointStatus;

/**
 * Data Transfer Object for summary representation of a RefreshmentPoint.
 */
public class RefreshmentPointSummaryDTO {
    private String id;
    private String name;
    private String location;
    private RefreshmentPointStatus status;

    public RefreshmentPointSummaryDTO(String id, String name, String location, RefreshmentPointStatus status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = status;
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

    public RefreshmentPointStatus getStatus() {
        return status;
    }
}
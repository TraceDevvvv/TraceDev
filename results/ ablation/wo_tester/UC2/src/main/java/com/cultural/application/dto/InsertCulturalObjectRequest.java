package com.cultural.application.dto;

import java.util.UUID;

/**
 * Request DTO for inserting a cultural object.
 */
public class InsertCulturalObjectRequest {
    private String name;
    private String description;
    private String type;
    private String location;
    private String requestId; // Added for operation tracking

    public InsertCulturalObjectRequest(String name, String description, String type, String location) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.requestId = UUID.randomUUID().toString(); // Auto-generate request ID
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getRequestId() {
        return requestId;
    }
}
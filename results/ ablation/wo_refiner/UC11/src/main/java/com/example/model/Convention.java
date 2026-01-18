package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a Convention entity.
 */
public class Convention {
    private String id;
    private String pointOfRestId;
    private String conventionData;
    private LocalDateTime timestamp; // Using LocalDateTime as in diagram

    public Convention(String id, String pointOfRestId, String conventionData, LocalDateTime timestamp) {
        this.id = id;
        this.pointOfRestId = pointOfRestId;
        this.conventionData = conventionData;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public String getConventionData() {
        return conventionData;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
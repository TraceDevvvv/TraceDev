package com.example.domain;

import java.time.LocalDateTime;

/**
 * Domain entity representing a single convention.
 */
public class Convention {

    private final String id;
    private final String details;
    private final PointOfRest pointOfRest;
    private final LocalDateTime timestamp;

    public Convention(String id, String details, PointOfRest pointOfRest, LocalDateTime timestamp) {
        this.id = id;
        this.details = details;
        this.pointOfRest = pointOfRest;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public PointOfRest getPointOfRest() {
        return pointOfRest;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
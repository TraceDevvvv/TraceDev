package com.example.infrastructure;

/**
 * Data transfer object representing convention data from the external eTour server.
 */
public class ConventionData {

    private final String id;
    private final String details;
    private final String pointId;
    private final String timestamp;

    public ConventionData(String id, String details, String pointId, String timestamp) {
        this.id = id;
        this.details = details;
        this.pointId = pointId;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public String getPointId() {
        return pointId;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
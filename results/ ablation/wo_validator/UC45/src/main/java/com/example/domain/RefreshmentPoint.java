package com.example.domain;

/**
 * Represents a refreshment point (point of restaurant).
 */
public class RefreshmentPoint {
    private String pointId;
    private String name;
    private String ownerId;

    public RefreshmentPoint(String id, String name, String ownerId) {
        this.pointId = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    public String getPointId() {
        return pointId;
    }

    public String getName() {
        return name;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
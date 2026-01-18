package com.example.model;

/**
 * Represents a refreshment point associated with a Convention.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private PointStatus status;

    public RefreshmentPoint(String id, String name, PointStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PointStatus getStatus() {
        return status;
    }

    /**
     * Activates the refreshment point by setting its status to ACTIVATED.
     */
    public void activate() {
        this.status = PointStatus.ACTIVATED;
    }

    /**
     * Determines if the refreshment point is designated.
     */
    public boolean isDesignated() {
        return status == PointStatus.DESIGNATED;
    }
}
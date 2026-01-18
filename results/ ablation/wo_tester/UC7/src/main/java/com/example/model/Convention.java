package com.example.model;

/**
 * Represents a Convention with data request and refreshment point.
 */
public class Convention {
    private String id;
    private ConventionStatus status;
    private DataRequest dataRequest;
    private RefreshmentPoint refreshmentPoint;

    public Convention(String id, ConventionStatus status, DataRequest dataRequest, RefreshmentPoint refreshmentPoint) {
        this.id = id;
        this.status = status;
        this.dataRequest = dataRequest;
        this.refreshmentPoint = refreshmentPoint;
    }

    public String getId() {
        return id;
    }

    public ConventionStatus getStatus() {
        return status;
    }

    public void setStatus(ConventionStatus status) {
        this.status = status;
    }

    public DataRequest getDataRequest() {
        return dataRequest;
    }

    public RefreshmentPoint getRefreshmentPoint() {
        return refreshmentPoint;
    }

    /**
     * Checks if the convention is active.
     */
    public boolean isActive() {
        return status == ConventionStatus.ACTIVE;
    }

    /**
     * Activates the convention by setting its status to ACTIVE.
     */
    public void activate() {
        this.status = ConventionStatus.ACTIVE;
    }

    /**
     * Validates preconditions for activation.
     * Assumes both data request and refreshment point must be present and valid.
     */
    public boolean validatePreconditions() {
        return dataRequest != null && dataRequest.hasData() &&
               refreshmentPoint != null && refreshmentPoint.isDesignated();
    }
}
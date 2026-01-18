package com.example.domain;

/**
 * Domain entity representing a Convention.
 */
public class Convention {
    private final String id;
    private ConventionStatus status;
    private ConventionDataDTO data;

    public Convention(String id) {
        this.id = id;
        this.status = ConventionStatus.PENDING_ACTIVATION;
    }

    public String getId() {
        return id;
    }

    public ConventionStatus getStatus() {
        return status;
    }

    public ConventionDataDTO getData() {
        return data;
    }

    /**
     * Activates the convention, changing its status to ACTIVE.
     */
    public void activate() {
        this.status = ConventionStatus.ACTIVE;
    }

    /**
     * Loads data into the convention (e.g., from point of rest request).
     * @param conventionDataDTO the data to load
     */
    public void loadData(ConventionDataDTO conventionDataDTO) {
        this.data = conventionDataDTO;
    }
}
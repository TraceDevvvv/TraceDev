package com.example.model;

import java.util.Date;
import java.util.List;

/**
 * Entity representing a convention history record.
 * Contains a reference to the associated PointOfRest.
 * Added getConventionHistory() method to satisfy Flow of Events.
 */
public class ConventionHistory {
    private String conventionId;
    private PointOfRest pointOfRest;
    private Date conventionDate;
    private String details;

    public ConventionHistory(String conventionId, PointOfRest pointOfRest, Date conventionDate, String details) {
        this.conventionId = conventionId;
        this.pointOfRest = pointOfRest;
        this.conventionDate = conventionDate;
        this.details = details;
    }

    public String getConventionId() {
        return conventionId;
    }

    public PointOfRest getPointOfRest() {
        return pointOfRest;
    }

    public Date getConventionDate() {
        return conventionDate;
    }

    public String getDetails() {
        return details;
    }

    /**
     * Added to satisfy Flow of Events (Step 2).
     * This method returns a list of ConventionHistory for the associated point of rest.
     * In a real scenario, this might delegate to a repository.
     * Here we return a singleton list for simplicity.
     */
    public List<ConventionHistory> getConventionHistory() {
        return List.of(this);
    }
}
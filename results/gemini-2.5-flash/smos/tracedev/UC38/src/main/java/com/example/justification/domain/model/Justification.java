package com.example.justification.domain.model;

/**
 * Represents the Justification entity in the domain model.
 * It encapsulates the core data and behavior related to a justification.
 */
public class Justification {
    private String id;
    private String details;
    private String status;
    private String absenceId;

    /**
     * Constructs a new Justification instance.
     *
     * @param id        The unique identifier for the justification.
     * @param details   The detailed description of the justification.
     * @param status    The current status of the justification (e.g., "Pending", "Approved", "Rejected").
     * @param absenceId The identifier of the absence this justification is related to.
     */
    public Justification(String id, String details, String status, String absenceId) {
        this.id = id;
        this.details = details;
        this.status = status;
        this.absenceId = absenceId;
    }

    /**
     * Gets the unique identifier of the justification.
     *
     * @return The justification ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the detailed description of the justification.
     *
     * @return The justification details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the detailed description of the justification.
     *
     * @param details The new justification details.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets the current status of the justification.
     *
     * @return The justification status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the justification.
     *
     * @param status The new justification status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the identifier of the absence this justification is related to.
     *
     * @return The absence ID.
     */
    public String getAbsenceId() {
        return absenceId;
    }
}
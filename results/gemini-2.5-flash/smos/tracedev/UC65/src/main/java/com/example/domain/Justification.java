package com.example.domain;

/**
 * Domain model for a Justification.
 */
public class Justification {
    private String id;
    private String description;
    private String status; // e.g., "Approved", "Pending", "Rejected"

    /**
     * Constructs a Justification object.
     * @param id The unique ID of the justification.
     * @param description The textual description of the justification.
     * @param status The status of the justification (e.g., Approved, Pending).
     */
    public Justification(String id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
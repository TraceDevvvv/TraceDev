/*
This class represents a Justification entity in the system.
It is a simple Plain Old Java Object (POJO) to hold justification data.
*/
package com.chatdev.eliminatejustification.models;
public class Justification {
    private String id;
    private String description;
    private String status;
    /**
     * Constructs a new Justification object.
     *
     * @param id The unique identifier of the justification.
     * @param description A brief description of the justification.
     * @param status The current status of the justification (e.g., "Pending", "Approved", "Eliminated").
     */
    public Justification(String id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }
    /**
     * Returns the ID of the justification.
     * @return The justification ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the justification.
     * @param id The new justification ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Returns the description of the justification.
     * @return The justification description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the justification.
     * @param description The new justification description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Returns the status of the justification.
     * @return The justification status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the status of the justification.
     * @param status The new justification status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Justification{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
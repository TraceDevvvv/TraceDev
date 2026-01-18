package com.example.convention.model;

/**
 * Represents a Convention in the system.
 * [R1] Convention class added to satisfy requirement R1.
 */
public class Convention {
    private String id;
    private String status; // e.g., "PENDING", "ACTIVE", "INACTIVE"
    private String requestedById; // ID of the RefreshmentPoint that requested this convention
    private String details;

    /**
     * Constructor for Convention.
     *
     * @param id The unique identifier of the convention.
     * @param status The current status of the convention.
     * @param requestedById The ID of the refreshment point requesting this convention.
     * @param details Additional details about the convention.
     */
    public Convention(String id, String status, String requestedById, String details) {
        this.id = id;
        this.status = status;
        this.requestedById = requestedById;
        this.details = details;
    }

    /**
     * Activates the convention by changing its status to "ACTIVE".
     * This method is called as part of the activation flow.
     */
    public void activate() {
        this.status = "ACTIVE";
        System.out.println("Convention " + id + " has been activated.");
    }

    /**
     * Deactivates the convention by changing its status to "INACTIVE".
     * (Not explicitly used in the provided sequence diagram, but part of class diagram).
     */
    public void deactivate() {
        this.status = "INACTIVE";
        System.out.println("Convention " + id + " has been deactivated.");
    }

    // Getters for attributes
    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getRequestedById() {
        return requestedById;
    }

    public String getDetails() {
        return details;
    }

    // Setters for attributes (if needed, only status is changed directly by method)
    public void setStatus(String status) {
        this.status = status;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
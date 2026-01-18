package com.example.justification.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Represents the Justification aggregate root.
 * Contains core business logic and state for a justification.
 * Corresponds to the Justification class in the Class Diagram.
 */
public class Justification {
    /**
     * Unique identifier for the justification.
     */
    private String id;

    /**
     * Title of the justification.
     */
    private String title;

    /**
     * Detailed description of the justification.
     */
    private String description;

    /**
     * Date when the justification was created.
     */
    private Date creationDate;

    /**
     * Current status of the justification (e.g., ACTIVE, DELETED).
     */
    private JustificationStatus status;

    /**
     * Constructor for creating a new Justification.
     *
     * @param id The unique identifier for the justification.
     * @param title The title of the justification.
     * @param description The description of the justification.
     * @param creationDate The date the justification was created.
     */
    public Justification(String id, String title, String description, Date creationDate) {
        this.id = Objects.requireNonNull(id, "Justification ID cannot be null");
        this.title = Objects.requireNonNull(title, "Justification title cannot be null");
        this.description = Objects.requireNonNull(description, "Justification description cannot be null");
        this.creationDate = Objects.requireNonNull(creationDate, "Creation date cannot be null");
        this.status = JustificationStatus.ACTIVE; // Default status for new justifications
    }

    /**
     * Marks the justification for deletion by changing its status to DELETED.
     * This is typically a soft-delete mechanism before potential physical removal.
     */
    public void markForDeletion() {
        if (this.status != JustificationStatus.DELETED) {
            setStatus(JustificationStatus.DELETED);
            System.out.println("Justification " + id + " marked for deletion.");
        }
    }

    /**
     * Checks if the justification is currently marked as DELETED.
     *
     * @return true if the status is DELETED, false otherwise.
     */
    public boolean isDeleted() {
        return this.status == JustificationStatus.DELETED;
    }

    /**
     * Sets the status of the justification. This is a private method to ensure
     * status changes are controlled by the aggregate's business rules.
     *
     * @param newStatus The new status to set.
     */
    private void setStatus(JustificationStatus newStatus) {
        // Here, one could add validation logic for status transitions
        this.status = newStatus;
    }

    // --- Getters for attributes ---
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public JustificationStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Justification{" +
               "id='" + id + '\'' +
               ", title='" + title + '\'' +
               ", status=" + status +
               '}';
    }
}
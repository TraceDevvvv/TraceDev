package com.example;

import java.util.Date;

/**
 * Represents a Feedback entity, extending AuditableEntity.
 * This is a domain model class which includes auditing fields
 * to satisfy quality requirement REQ-004.
 */
public class Feedback extends AuditableEntity {
    private String id;
    private String siteId;
    private String comment;
    private String status; // e.g., "PENDING", "RESOLVED"

    /**
     * Constructs a new Feedback instance.
     * @param id The unique identifier for the feedback.
     * @param siteId The ID of the site this feedback is associated with.
     * @param comment The comment text of the feedback.
     * @param status The current status of the feedback.
     */
    public Feedback(String id, String siteId, String comment, String status) {
        this.id = id;
        this.siteId = siteId;
        this.comment = comment;
        this.status = status;
        // Initialize auditing fields, though they will be explicitly set on modification
        // These fields are inherited from AuditableEntity
        this.setLastModifiedBy("System");
        this.setLastModifiedDate(new Date());
    }

    /**
     * Gets the unique identifier of the feedback.
     * @return The feedback's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the feedback.
     * @param id The new feedback ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the ID of the site this feedback belongs to.
     * @return The site's ID.
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Sets the ID of the site this feedback belongs to.
     * @param siteId The new site ID.
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * Gets the comment text of the feedback.
     * @return The feedback comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets a new comment text for the feedback.
     * @param newComment The new comment string.
     */
    public void setComment(String newComment) {
        this.comment = newComment;
    }

    /**
     * Gets the current status of the feedback.
     * @return The feedback status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the feedback.
     * @param status The new feedback status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
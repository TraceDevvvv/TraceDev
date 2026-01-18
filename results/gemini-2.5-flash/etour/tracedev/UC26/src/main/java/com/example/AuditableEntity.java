package com.example;

import java.util.Date;

/**
 * Abstract base class for entities that require auditing information.
 * Stores who last modified the entity and when.
 * (Added to satisfy Quality Requirement: Integrity and Persistence REQ-004)
 */
public abstract class AuditableEntity {
    private String lastModifiedBy;
    private Date lastModifiedDate;

    /**
     * Gets the user who last modified this entity.
     * @return The identifier of the user who last modified the entity.
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the user who last modified this entity.
     * This method is protected as it's intended for internal use by subclasses
     * or service layers responsible for auditing.
     * @param user The identifier of the user who last modified the entity.
     */
    protected void setLastModifiedBy(String user) {
        this.lastModifiedBy = user;
    }

    /**
     * Gets the date and time when this entity was last modified.
     * @return The date of the last modification.
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Sets the date and time when this entity was last modified.
     * This method is protected as it's intended for internal use by subclasses
     * or service layers responsible for auditing.
     * @param date The date of the last modification.
     */
    protected void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }
}
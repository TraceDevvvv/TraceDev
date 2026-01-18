package com.example.justification.domain;

/**
 * Enumeration representing the possible statuses of a Justification.
 * Corresponds to the JustificationStatus enum in the Class Diagram.
 */
public enum JustificationStatus {
    /**
     * The justification is active and currently in use.
     */
    ACTIVE,
    /**
     * The justification has been marked for deletion or soft-deleted.
     */
    DELETED,
    /**
     * The justification has been archived and is no longer active but retained for records.
     */
    ARCHIVED
}
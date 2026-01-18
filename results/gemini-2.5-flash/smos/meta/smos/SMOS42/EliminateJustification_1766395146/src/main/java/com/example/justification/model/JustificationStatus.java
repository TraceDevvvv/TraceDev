package com.example.justification.model;

/**
 * Enum representing the possible statuses of a Justification.
 * This helps in tracking the lifecycle and state of a justification within the system.
 */
public enum JustificationStatus {
    /**
     * Indicates that the justification is currently active and valid.
     */
    ACTIVE,

    /**
     * Indicates that the justification has been eliminated or marked as deleted.
     * This status is typically set when an administrator performs the "Eliminate Justification" action.
     */
    DELETED,

    /**
     * Represents a pending status, perhaps awaiting approval or further action.
     * Added for completeness, though not explicitly mentioned in the use case,
     * it's a common state in such systems.
     */
    PENDING
}
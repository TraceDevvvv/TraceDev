package com.absenceapp;

/**
 * Enum representing the status of an absence record.
 */
public enum AbsenceStatus {
    PENDING,
    APPROVED,
    CANCELED,
    MODIFIED
    // Added for Sequence Diagram clarity if needed to differentiate DTO states.
    // The DTO status can indicate "ADDED" or "DELETED" for frontend handling.
}
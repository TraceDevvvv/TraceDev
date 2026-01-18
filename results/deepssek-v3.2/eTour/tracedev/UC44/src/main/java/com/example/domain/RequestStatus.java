package com.example.domain;

/**
 * Enumeration representing the possible statuses of a ConventionRequest.
 */
public enum RequestStatus {
    DRAFT,
    PENDING_VALIDATION,
    AWAITING_CONFIRMATION,
    SUBMITTED,
    SENT,
    CANCELLED,
    ERRORED
}
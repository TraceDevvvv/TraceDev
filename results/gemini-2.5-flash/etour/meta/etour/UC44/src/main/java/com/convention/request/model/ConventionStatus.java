package com.convention.request.model;

/**
 * Enumeration for the possible statuses of a Convention.
 * This helps in tracking the lifecycle of a convention request.
 */
public enum ConventionStatus {
    /**
     * Initial status when a convention request is first created and awaiting confirmation/submission to agency.
     */
    PENDING,
    /**
     * Status indicating the convention request has been successfully sent to the agency and is awaiting their approval.
     */
    SENT_TO_AGENCY,
    /**
     * Status indicating the agency has approved the convention.
     */
    APPROVED,
    /**
     * Status indicating the agency has rejected the convention.
     */
    REJECTED,
    /**
     * Status indicating the convention request was cancelled by the operator.
     */
    CANCELLED,
    /**
     * Status indicating an error occurred during processing or communication with the agency.
     */
    ERROR
}
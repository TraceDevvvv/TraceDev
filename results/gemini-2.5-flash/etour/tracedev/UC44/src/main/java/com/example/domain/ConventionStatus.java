package com.example.domain;

/**
 * Enumeration for the possible statuses of a Convention.
 */
public enum ConventionStatus {
    PENDING_VERIFICATION, // Initial status after creation
    VERIFIED,             // After internal verification
    SENT_TO_AGENCY,       // Successfully notified the external agency
    REJECTED,             // Rejected during verification or by agency
    CANCELLED,            // Explicitly cancelled by operator (REQ-002)
    FAILED_TO_NOTIFY      // Failed to notify external agency due to connection issues (REQ-007)
}
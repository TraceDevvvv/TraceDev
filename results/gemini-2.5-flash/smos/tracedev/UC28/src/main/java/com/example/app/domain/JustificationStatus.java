package com.example.app.domain;

import com.example.app.Domain;

/**
 * Represents the status of a justification for absence.
 */
public enum JustificationStatus implements Domain {
    PENDING,
    APPROVED,
    REJECTED
}
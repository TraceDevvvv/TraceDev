package com.example.domain;

import java.util.UUID;

/**
 * Represents a Convention domain entity.
 * This class encapsulates the data and business rules related to a convention.
 */
public class Convention {
    private ConventionId id;
    private String agreementDetails;
    private ConventionStatus status;

    /**
     * Private constructor to enforce creation via the static factory method.
     *
     * @param id The unique identifier for the convention.
     * @param agreementDetails The details of the agreement.
     * @param status The current status of the convention.
     */
    private Convention(ConventionId id, String agreementDetails, ConventionStatus status) {
        this.id = id;
        this.agreementDetails = agreementDetails;
        this.status = status;
    }

    /**
     * Factory method to create a new Convention instance.
     * A newly created convention will have a generated ID and an initial status of PENDING_VERIFICATION.
     *
     * @param details The agreement details for the new convention.
     * @return A new Convention object.
     */
    public static Convention create(String details) {
        // Generate a new unique ID for the convention
        ConventionId newId = new ConventionId(UUID.randomUUID().toString());
        // Initialize with PENDING_VERIFICATION status as per business rule
        return new Convention(newId, details, ConventionStatus.PENDING_VERIFICATION);
    }

    /**
     * Updates the status of the convention.
     *
     * @param newStatus The new status to set for the convention.
     */
    public void updateStatus(ConventionStatus newStatus) {
        this.status = newStatus;
        // In a real application, additional business rules related to status transitions
        // (e.g., cannot change from REJECTED to VERIFIED) would be implemented here.
    }

    // --- Getters ---
    public ConventionId getId() {
        return id;
    }

    public String getAgreementDetails() {
        return agreementDetails;
    }

    public ConventionStatus getStatus() {
        return status;
    }

    // For simplicity, setters are not provided to maintain domain integrity.
    // Modifications should happen via explicit methods like updateStatus.
}
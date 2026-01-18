package com.example.domain;

/**
 * Command object for activating a convention.
 * Contains necessary identifiers for the convention and the operator.
 */
public class ConventionActivationCommand {
    private final String conventionId;
    private final String agencyOperatorId;
    private final boolean confirmed; // Assumed field to differentiate between first and second call

    public ConventionActivationCommand(String conventionId, String agencyOperatorId) {
        this(conventionId, agencyOperatorId, false);
    }

    public ConventionActivationCommand(String conventionId, String agencyOperatorId, boolean confirmed) {
        this.conventionId = conventionId;
        this.agencyOperatorId = agencyOperatorId;
        this.confirmed = confirmed;
    }

    public String getConventionId() {
        return conventionId;
    }

    public String getAgencyOperatorId() {
        return agencyOperatorId;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
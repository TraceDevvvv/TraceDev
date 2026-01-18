package com.example.application;

/**
 * Command object for requesting a new Convention.
 * This carries the necessary data from the presentation layer to the application service.
 */
public class ConventionRequestCommand {
    private String agreementDetails;

    /**
     * Constructs a ConventionRequestCommand.
     *
     * @param agreementDetails The details of the agreement for the new convention.
     */
    public ConventionRequestCommand(String agreementDetails) {
        this.agreementDetails = agreementDetails;
    }

    public String getAgreementDetails() {
        return agreementDetails;
    }

    // A setter might be present if the DTO mapping library uses it, but for explicit command it's often immutable
    // public void setAgreementDetails(String agreementDetails) { this.agreementDetails = agreementDetails; }
}
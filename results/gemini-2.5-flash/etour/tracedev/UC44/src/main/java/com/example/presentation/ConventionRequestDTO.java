package com.example.presentation;

/**
 * Data Transfer Object for incoming Convention request data.
 * Used by the Presentation Layer to receive data from the client.
 */
public class ConventionRequestDTO {
    private String agreementDetails;

    // Default constructor for (de)serialization frameworks
    public ConventionRequestDTO() {
    }

    /**
     * Constructs a ConventionRequestDTO.
     *
     * @param agreementDetails The agreement details provided by the client.
     */
    public ConventionRequestDTO(String agreementDetails) {
        this.agreementDetails = agreementDetails;
    }

    public String getAgreementDetails() {
        return agreementDetails;
    }

    public void setAgreementDetails(String agreementDetails) {
        this.agreementDetails = agreementDetails;
    }

    @Override
    public String toString() {
        return "ConventionRequestDTO{" +
               "agreementDetails='" + agreementDetails + '\'' +
               '}';
    }
}
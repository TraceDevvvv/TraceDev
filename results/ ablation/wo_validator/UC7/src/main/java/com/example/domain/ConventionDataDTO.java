package com.example.domain;

import java.util.Map;

/**
 * Data Transfer Object containing convention details.
 */
public class ConventionDataDTO {
    private final String agreementData;
    private final Map<String, String> pointOfRestDetails;

    public ConventionDataDTO(String agreementData, Map<String, String> pointOfRestDetails) {
        this.agreementData = agreementData;
        this.pointOfRestDetails = pointOfRestDetails;
    }

    public String getAgreementData() {
        return agreementData;
    }

    public Map<String, String> getPointOfRestDetails() {
        return pointOfRestDetails;
    }
}
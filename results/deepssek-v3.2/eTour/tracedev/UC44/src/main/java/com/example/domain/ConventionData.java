package com.example.domain;

import java.util.Date;

/**
 * Value Object representing the data of a convention request.
 * Ensures data validity and immutability.
 */
public class ConventionData {
    private final String agreementTerms;
    private final Date startDate;
    private final Date endDate;
    private final ContactDetails contactDetails;

    public ConventionData(String agreementTerms, Date startDate, Date endDate, ContactDetails contactDetails) {
        this.agreementTerms = agreementTerms;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contactDetails = contactDetails;
    }

    /**
     * Validates the convention data.
     * @return true if data is valid
     */
    public boolean isValid() {
        if (agreementTerms == null || agreementTerms.trim().isEmpty()) {
            return false;
        }
        if (startDate == null || endDate == null) {
            return false;
        }
        if (endDate.before(startDate)) {
            return false;
        }
        if (contactDetails == null || !contactDetails.isValid()) {
            return false;
        }
        return true;
    }

    public String getAgreementTerms() {
        return agreementTerms;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }
}
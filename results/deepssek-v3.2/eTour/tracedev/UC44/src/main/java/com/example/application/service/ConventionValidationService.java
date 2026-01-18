package com.example.application.service;

import com.example.domain.ConventionData;
import com.example.domain.ConventionRequest;
import com.example.domain.ValidationResult;
import java.util.Date;
import java.util.List;

public class ConventionValidationService {

    public ValidationResult validateData(ConventionData data) {
        ValidationResult result = new ValidationResult();

        if (data == null) {
            result.addError("ConventionData cannot be null");
            return result;
        }

        checkRequiredFields(data, result);
        validateDates(data, result);
        validateContactInfo(data, result);

        return result;
    }

    private void checkRequiredFields(ConventionData data, ValidationResult result) {
        if (data.getAgreementTerms() == null || data.getAgreementTerms().trim().isEmpty()) {
            result.addError("Agreement terms are required");
        }

        if (data.getStartDate() == null) {
            result.addError("Start date is required");
        }

        if (data.getEndDate() == null) {
            result.addError("End date is required");
        }
    }

    private void validateDates(ConventionData data, ValidationResult result) {
        if (data.getStartDate() != null && data.getEndDate() != null) {
            if (data.getEndDate().before(data.getStartDate())) {
                result.addError("End date must be after start date");
            }
            if (data.getStartDate().before(new Date())) {
                result.addError("Start date must be in the future");
            }
        }
    }

    private void validateContactInfo(ConventionData data, ValidationResult result) {
        if (data.getContactDetails() == null) {
            result.addError("Contact details are required");
        } else {
            if (data.getContactDetails().getName() == null || data.getContactDetails().getName().trim().isEmpty()) {
                result.addError("Contact name is required");
            }
            if (data.getContactDetails().getEmail() == null || data.getContactDetails().getEmail().trim().isEmpty()) {
                result.addError("Contact email is required");
            }
            if (data.getContactDetails().getPhone() == null || data.getContactDetails().getPhone().trim().isEmpty()) {
                result.addError("Contact phone is required");
            }
        }
    }

    public boolean validateBusinessRules(ConventionRequest request) {
        return true;
    }
}
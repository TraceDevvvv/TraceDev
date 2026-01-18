package com.culturalheritage.application.service;

import com.culturalheritage.application.request.DeleteCulturalHeritageRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Validates delete requests.
 */
public class RequestValidator {
    public ValidationResult validateDeleteRequest(DeleteCulturalHeritageRequest request) {
        List<String> errors = new ArrayList<>();
        if (request.getCulturalHeritageId() == null || request.getCulturalHeritageId().trim().isEmpty()) {
            errors.add("Cultural heritage ID is required");
        }
        if (request.getConfirmationToken() == null || request.getConfirmationToken().trim().isEmpty()) {
            errors.add("Confirmation token is required");
        }
        if (request.getOperatorId() == null || request.getOperatorId().trim().isEmpty()) {
            errors.add("Operator ID is required");
        }
        return new ValidationResult(errors.isEmpty(), errors);
    }
}
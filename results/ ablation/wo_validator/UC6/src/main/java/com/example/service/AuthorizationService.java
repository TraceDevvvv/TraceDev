package com.example.service;

import com.example.model.AgencyOperator;

/**
 * Service for handling authorization checks.
 */
public class AuthorizationService {

    /**
     * Checks if the operator is authorized for deletion.
     *
     * @param operator the agency operator.
     * @return true if authorized.
     */
    public boolean isAuthorizedForDeletion(AgencyOperator operator) {
        // Assumption: For this example, only operators with id starting with "AUTH" are authorized.
        // This simulates the authorization logic.
        return operator.getId() != null && operator.getId().startsWith("AUTH");
    }
}
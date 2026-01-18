package com.example.domain;

/**
 * Service for validating agency-related preconditions.
 */
public class AgencyService {

    /**
     * Checks if the given agency ID corresponds to a designated point of rest.
     */
    public boolean isDesignatedPointOfRest(String agencyId) {
        // In a real implementation, this might query a database or external service.
        // For simulation, we assume any non‑null, non‑empty agencyId is valid.
        return agencyId != null && !agencyId.trim().isEmpty();
    }
}
package com.system.service;

/**
 * Simple implementation of agency service.
 * Assumes agency is active if it exists.
 */
public class AgencyService implements IAgencyService {
    @Override
    public boolean isAgencyActive(String agencyId) {
        // In a real system, this would query a database or external service.
        // For simplicity, we assume agency with ID "AG001" is active.
        return "AG001".equals(agencyId);
    }
}
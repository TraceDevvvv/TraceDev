package com.touristagency.service;

import com.touristagency.model.Tourist;
import com.touristagency.repository.TouristRepository;

/**
 * Service for managing tourist account status.
 * Handles business logic for enabling/disabling accounts.
 */
public class TouristAccountService {
    private final TouristRepository touristRepository;

    public TouristAccountService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    /**
     * Toggle the account status of a tourist (enable/disable).
     * Simulates confirmation and updates the status.
     *
     * @param touristId the ID of the tourist
     * @param confirmed whether the agency operator confirmed the operation
     * @return a result message describing the outcome
     */
    public String toggleAccountStatus(String touristId, boolean confirmed) {
        // Quality requirement: operation within 5 seconds.
        long startTime = System.currentTimeMillis();

        if (touristId == null || touristId.trim().isEmpty()) {
            return "Error: Tourist ID cannot be empty.";
        }

        Tourist tourist = touristRepository.findById(touristId);
        if (tourist == null) {
            return "Error: Tourist with ID " + touristId + " not found.";
        }

        if (!confirmed) {
            return "Operation cancelled: confirmation not given.";
        }

        // Toggle the account status
        boolean newStatus = !tourist.isAccountEnabled();
        tourist.setAccountEnabled(newStatus);

        boolean updated = touristRepository.update(tourist);
        if (!updated) {
            return "Error: Failed to update tourist account.";
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        String status = newStatus ? "enabled" : "disabled";
        return "Success: Tourist account " + touristId + " has been " + status + ". (Operation took " + duration + " ms)";
    }

    /**
     * Get current status of a tourist account.
     *
     * @param touristId the tourist ID
     * @return status message
     */
    public String getAccountStatus(String touristId) {
        Tourist tourist = touristRepository.findById(touristId);
        if (tourist == null) {
            return "Error: Tourist not found.";
        }
        return "Tourist " + touristId + " account is " + (tourist.isAccountEnabled() ? "ENABLED" : "DISABLED");
    }
}
package com.example.service;

import com.example.dto.PreferenceDTO;

/**
 * Service interface for preference operations.
 * Defines the contract for getting and updating preferences.
 */
public interface PreferenceService {
    /**
     * Retrieves preferences for a given tourist.
     * @param touristId the ID of the tourist
     * @return PreferenceDTO containing the tourist's preferences
     */
    PreferenceDTO getPreferences(String touristId);

    /**
     * Updates preferences for a given tourist.
     * @param touristId the ID of the tourist
     * @param dto the new preference data
     * @return true if update succeeded, false otherwise
     */
    boolean updatePreferences(String touristId, PreferenceDTO dto);
}
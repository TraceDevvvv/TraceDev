package com.example.repository;

import com.example.model.Preference;

/**
 * Repository interface for Preference entities.
 * Abstracts data access operations.
 */
public interface PreferenceRepository {
    /**
     * Finds a preference by the associated tourist ID.
     * @param touristId the tourist ID
     * @return the Preference, or null if not found
     */
    Preference findByTouristId(String touristId);

    /**
     * Saves a preference entity.
     * @param preference the preference to save
     * @return the saved preference
     */
    Preference save(Preference preference);
}
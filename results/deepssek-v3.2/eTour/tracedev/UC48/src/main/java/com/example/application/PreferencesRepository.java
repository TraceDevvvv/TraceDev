package com.example.application;

import com.example.domain.Preferences;

/**
 * Repository interface for Preferences persistence.
 * This matches the sequence diagram participant "RepositoryInterface".
 */
public interface PreferencesRepository {
    /**
     * Finds preferences for a given user.
     * @param userId the user identifier
     * @return the Preferences object for the user
     */
    Preferences findByUserId(String userId);

    /**
     * Saves preferences for a given user.
     * @param userId the user identifier
     * @param preferences the Preferences object to save
     */
    void save(String userId, Preferences preferences);
}
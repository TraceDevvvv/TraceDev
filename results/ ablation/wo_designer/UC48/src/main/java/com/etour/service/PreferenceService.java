package com.etour.service;

import com.etour.model.UserPreference;

/**
 * Service for handling preference operations.
 */
public class PreferenceService {
    // Simulates stored preferences (in a real application, this would be a database)
    private UserPreference currentPreference = new UserPreference("English", "Light", true, "USD", 50);

    /**
     * Loads the current preferences of the authenticated user.
     * @return the current UserPreference object
     */
    public UserPreference loadPreferences() {
        // Simulate a small delay for loading
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return currentPreference;
    }

    /**
     * Updates the preferences after confirmation.
     * @param newPreference the new preference object
     * @return true if update was successful, false otherwise
     */
    public boolean updatePreferences(UserPreference newPreference) {
        // Simulate server storage delay
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        // Validate the new preference (simple validation)
        if (newPreference.getLanguage() == null || newPreference.getLanguage().isEmpty()) {
            return false;
        }
        if (newPreference.getSearchRadius() <= 0) {
            return false;
        }
        currentPreference = newPreference;
        return true;
    }

    /**
     * Simulates a server connection check.
     * @return true if connection to server ETOUR is active, false otherwise
     */
    public boolean isServerConnected() {
        // Simulate a random connection status (90% chance of being connected)
        return Math.random() > 0.1;
    }
}
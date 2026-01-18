package com.example.controller;

import com.example.dto.PreferenceDTO;
import com.example.service.PreferenceService;

/**
 * Controller handling preference-related HTTP requests.
 * Acts as the boundary between UI and service layer.
 */
public class PreferenceController {
    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    /**
     * Retrieves the current preferences for a tourist to display in edit form.
     * Corresponds to showEditForm in sequence diagram.
     * @param touristId the ID of the tourist
     * @return PreferenceDTO containing current preference values
     */
    public PreferenceDTO showEditForm(String touristId) {
        // In a real application, this might involve returning a view model.
        // Here, we just delegate to service layer to fetch preferences.
        return preferenceService.getPreferences(touristId);
    }

    /**
     * Handles form submission for updating preferences.
     * Includes validation and confirmation step as per sequence diagram.
     * @param touristId the ID of the tourist
     * @param dto the updated preference data
     * @return true if update was successful, false otherwise
     */
    public boolean updatePreferences(String touristId, PreferenceDTO dto) {
        // Validate form data (simplified)
        validateFormData();
        // Show confirmation (in a real scenario this might be handled by UI)
        showConfirmation(dto);
        // The actual update is performed after confirmation via confirmUpdate.
        // For this method, we assume it's a preliminary step and return false
        // because actual update hasn't happened yet.
        return false;
    }

    /**
     * Simulates validation of form data.
     * Called from updatePreferences as per sequence diagram.
     */
    private void validateFormData() {
        // In a real application, validate fields (e.g., non‑null, length, format).
        // This is a placeholder implementation.
        System.out.println("Validating form data...");
    }

    /**
     * Displays a confirmation dialog with the preference data.
     * Called from updatePreferences as per sequence diagram.
     * @param dto the preference data to confirm
     */
    public void showConfirmation(PreferenceDTO dto) {
        // In a real application, this would trigger a UI confirmation dialog.
        // Here we just print to console.
        System.out.println("Showing confirmation dialog for: " + dto);
    }

    /**
     * Finalizes the preference update after user confirmation.
     * Corresponds to confirmUpdate in sequence diagram.
     * @param touristId the ID of the tourist
     * @param dto the confirmed preference data
     * @return true if update succeeded, false otherwise
     */
    public boolean confirmUpdate(String touristId, PreferenceDTO dto) {
        // Delegate to service layer to perform the actual update.
        boolean success = preferenceService.updatePreferences(touristId, dto);
        if (success) {
            System.out.println("Successfully updated preferences for tourist: " + touristId);
        } else {
            System.out.println("Failed to update preferences for tourist: " + touristId);
        }
        return success;
    }
}
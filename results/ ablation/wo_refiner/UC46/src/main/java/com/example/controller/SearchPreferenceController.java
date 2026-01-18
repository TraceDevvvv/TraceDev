package com.example.controller;

import com.example.model.*;
import com.example.service.AuthenticationService;
import com.example.service.PreferenceService;
import java.util.Arrays;
import java.util.List;

/**
 * Controller that handles the flow of search preference modification.
 * Implements the sequence diagram interactions.
 */
public class SearchPreferenceController {
    private PreferenceService preferenceService;
    private AuthenticationService authService;

    // For simulation, we assume some static data
    private static final List<String> AVAILABLE_CATEGORIES = Arrays.asList("Museum", "Park", "Restaurant", "Shopping");
    private static final List<String> PRICE_RANGES = Arrays.asList("$", "$$", "$$$", "$$$$");

    public SearchPreferenceController(PreferenceService preferenceService, AuthenticationService authService) {
        this.preferenceService = preferenceService;
        this.authService = authService;
    }

    /**
     * Shows the preferences form for the current tourist.
     * In a real application, touristId would be obtained from session/security context.
     */
    public PreferenceFormDTO showPreferencesForm() {
        // For demo, we use a hardcoded tourist ID; in reality this would be passed.
        String touristId = "tourist123";

        // Authentication check as per sequence diagram
        boolean verified = authService.verifyTourist(touristId);
        if (!verified) {
            throw new RuntimeException("Tourist not authenticated");
        }

        // Load existing preferences
        SearchPreference pref = preferenceService.loadPreferences(touristId);
        PreferenceDTO current = new PreferenceDTO(
                pref.getMaxDistance(),
                pref.getPriceRange(),
                pref.getPreferredCategories(),
                pref.getRatingThreshold()
        );

        // Build form DTO with current preferences and available options
        return new PreferenceFormDTO(current, AVAILABLE_CATEGORIES, PRICE_RANGES);
    }

    /**
     * Updates preferences based on the provided DTO.
     */
    public ResponseDTO updatePreferences(PreferenceDTO preferenceDTO) {
        String touristId = "tourist123";
        try {
            // Form validation
            if (!validateForm(preferenceDTO)) {
                return new ResponseDTO(false, "Form validation failed");
            }

            // Delegate to service
            String confirmationId = preferenceService.updatePreferences(touristId, preferenceDTO);
            // In sequence diagram, response indicates needsConfirmation=true
            return new ResponseDTO(true, "Preferences updated, confirmation required", confirmationId);
        } catch (RuntimeException e) {
            // Handle exceptions, e.g., validation failed or connection interruption
            return new ResponseDTO(false, "Update failed: " + e.getMessage());
        }
    }

    /**
     * Confirms the update using a confirmation ID.
     */
    public ResponseDTO confirmUpdate(String confirmationId) {
        try {
            preferenceService.confirmUpdate(confirmationId);
            return new ResponseDTO(true, "Update confirmed successfully");
        } catch (Exception e) {
            return new ResponseDTO(false, "Confirmation failed: " + e.getMessage());
        }
    }

    /**
     * Handles user cancellation operation.
     */
    public ResponseDTO cancelOperation() {
        // Exit condition: User cancellation as per sequence diagram note
        return new ResponseDTO(false, "Operation cancelled by user");
    }

    /**
     * Handles connection interruptions (Quality Requirement ETour).
     */
    public void handleConnectionInterruption() {
        // Quality Requirement: Handle connection interruptions (ETOUR)
        preferenceService.handleConnectionInterruption();
    }

    /**
     * Validate the form data.
     */
    private boolean validateForm(PreferenceDTO dto) {
        // Basic validation: maxDistance positive, ratingThreshold between 0 and 5
        if (dto.getMaxDistance() != null && dto.getMaxDistance() < 0) {
            return false;
        }
        if (dto.getRatingThreshold() != null && 
            (dto.getRatingThreshold() < 0.0 || dto.getRatingThreshold() > 5.0)) {
            return false;
        }
        return true;
    }
}
package com.example.controller;

import com.example.dto.PreferenceSetDTO;
import com.example.model.PreferenceSet;
import com.example.service.PreferenceService;
import com.example.service.AuthenticationService;
import java.util.Map;

/**
 * Control class for preference operations.
 */
public class PreferenceController {
    private PreferenceService preferenceService;
    private AuthenticationService authenticationService;

    public PreferenceController(PreferenceService preferenceService, AuthenticationService authenticationService) {
        this.preferenceService = preferenceService;
        this.authenticationService = authenticationService;
    }

    public PreferenceSetDTO loadPreferences(String touristId) {
        // Validate authentication as per sequence diagram
        if (!validateAuthentication(touristId)) {
            System.out.println("Authentication failed for tourist: " + touristId);
            return null;
        }

        PreferenceSet preferences = preferenceService.getPreferencesForTourist(touristId);
        if (preferences == null) {
            return new PreferenceSetDTO();
        }
        return preferenceService.convertToDTO(preferences);
    }

    public boolean updatePreferences(String touristId, PreferenceSetDTO dto) {
        // Validate authentication as per sequence diagram
        if (!validateAuthentication(touristId)) {
            System.out.println("Authentication failed for tourist: " + touristId);
            return false;
        }

        // Convert DTO to entity
        PreferenceSet preferences = new PreferenceSet(
            dto.getLanguage(),
            dto.getTheme(),
            dto.isNotificationsEnabled(),
            dto.getAccessibilityOptions()
        );

        return preferenceService.updatePreferences(touristId, preferences);
    }

    public boolean confirmChanges() {
        // In a real application, this might involve additional business logic
        return true;
    }

    // Added for validation before submission
    public boolean validateInput(PreferenceSetDTO dto) {
        if (dto == null) {
            return false;
        }
        PreferenceSet preferences = new PreferenceSet(
            dto.getLanguage(),
            dto.getTheme(),
            dto.isNotificationsEnabled(),
            dto.getAccessibilityOptions()
        );
        return preferenceService.validatePreferences(preferences);
    }

    private boolean validateAuthentication(String touristId) {
        return authenticationService.authenticate(touristId);
    }

    // Missing sequence diagram message: success result
    public void sendSuccessResult() {
        // This method corresponds to m30: success result
        System.out.println("Controller sending success result to Form");
    }
}
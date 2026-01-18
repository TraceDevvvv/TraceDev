package com.example.controller;

import com.example.model.GenericPreference;
import com.example.form.PreferenceForm;
import com.example.service.AuthenticationService;

/**
 * Controller that orchestrates the preference modification flow.
 * Added to satisfy requirement 5.
 */
public class PreferenceController {
    private PreferenceForm preferenceForm;
    private AuthenticationService authService;

    public PreferenceController(PreferenceForm preferenceForm, AuthenticationService authService) {
        this.preferenceForm = preferenceForm;
        this.authService = authService;
    }

    /**
     * Orchestrates the entire preference modification flow.
     * Linked to sequence message m3.
     */
    public boolean orchestratePreferenceFlow(String touristId) {
        System.out.println("PreferenceController: Orchestrating flow for tourist " + touristId);
        // Sequence diagram: first authenticate
        boolean authResult = authService.authenticate(touristId);
        if (!authResult) {
            // Authentication failed, exit condition
            System.out.println("PreferenceController: Authentication failed for tourist " + touristId);
            return false;
        }
        // Then display preference modification interface (m4)
        preferenceForm.displayForm(null); // In real flow, data would be fetched first.
        return true;
    }

    /**
     * Handles a preference update.
     */
    public boolean handlePreferenceUpdate(GenericPreference preference) {
        System.out.println("PreferenceController: Handling update for preference " + preference.getPreferenceId());
        return true;
    }

    /**
     * Cancels an operation.
     * Added to satisfy requirement 13.
     */
    public void cancelOperation(String operationId) {
        System.out.println("PreferenceController: Cancelling operation " + operationId);
    }
}
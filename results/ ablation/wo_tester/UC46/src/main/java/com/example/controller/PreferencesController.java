package com.example.controller;

import com.example.application.UpdatePreferencesUseCase;
import com.example.application.GetPreferencesUseCase;
import com.example.application.Result;
import com.example.domain.SearchPreferences;
import com.example.dto.PreferencesDTO;
import com.example.service.AuthenticationService;
import com.example.service.ConfirmationService;

/**
 * Boundary controller that handles requests related to search preferences.
 * Orchestrates the use cases and manages the user interaction flow.
 */
public class PreferencesController {
    private UpdatePreferencesUseCase updateUseCase;
    private GetPreferencesUseCase getUseCase;
    private AuthenticationService authenticationService;
    private ConfirmationService confirmationService;

    public PreferencesController(UpdatePreferencesUseCase updateUseCase,
                                 GetPreferencesUseCase getUseCase,
                                 AuthenticationService authenticationService,
                                 ConfirmationService confirmationService) {
        this.updateUseCase = updateUseCase;
        this.getUseCase = getUseCase;
        this.authenticationService = authenticationService;
        this.confirmationService = confirmationService;
    }

    // Handles the initial request to update preferences (includes authentication check)
    public Result updatePreferencesRequest(String touristId, String authToken) {
        // Entry Condition: Authentication check
        if (!authenticationService.isAuthenticated(touristId)) {
            return new Result(false, "Authentication failed", "AUTH_ERROR");
        }
        // Authentication successful - proceed
        // Note: In a real scenario, the authToken would be validated.
        return handleUpdateRequest(touristId, showPreferencesForm(touristId));
    }

    // Shows the preferences form by retrieving current preferences
    public PreferencesDTO showPreferencesForm(String touristId) {
        SearchPreferences currentPrefs = getUseCase.execute(touristId);
        if (currentPrefs == null) {
            // Return a default DTO if no preferences exist
            return new PreferencesDTO(new String[]{}, 0.0, 1000.0, 10, new String[]{});
        }
        // Convert domain object to DTO for the form
        return new PreferencesDTO(
                currentPrefs.getCategories(),
                currentPrefs.getPriceRange().getMin(),
                currentPrefs.getPriceRange().getMax(),
                currentPrefs.getDistanceLimit(),
                currentPrefs.getKeywords()
        );
    }

    // Displays the form with the given preferences DTO (for UI)
    public void displayForm(PreferencesDTO preferencesDTO) {
        // In a real application, this would update the UI.
        System.out.println("Displaying preferences form with data: " + preferencesDTO);
    }

    // Displays a confirmation dialog (for UI)
    public void displayConfirmationDialog() {
        // In a real application, this would show a confirmation dialog.
        System.out.println("Displaying confirmation dialog.");
    }

    // Handles the update request from the tourist
    public Result handleUpdateRequest(String touristId, PreferencesDTO preferencesDTO) {
        // Delegates to the use case
        return updateUseCase.execute(touristId, preferencesDTO);
    }

    // Handles confirmation from the tourist (called after confirmation dialog)
    public Result handleConfirmation(String touristId, PreferencesDTO preferencesDTO) {
        // In this simplified flow, we proceed with the update.
        // In a real scenario, this might trigger the update use case again.
        return updateUseCase.execute(touristId, preferencesDTO);
    }

    // Receives confirmation from the tourist (UI callback)
    public void receiveConfirmation() {
        // This method would be called by the UI when the tourist confirms.
        System.out.println("Confirmation received from tourist.");
    }

    // Handles cancellation of the operation by the tourist
    public Result cancelOperation(String touristId) {
        return new Result(false, "Operation cancelled", "CANCELLED");
    }

    // New methods for sequence diagram traceability
    public void displayPreferencesForm(PreferencesDTO preferences) {
        displayForm(preferences);
    }

    public void confirmChanges() {
        // This would be called by the UI when tourist confirms changes
        receiveConfirmation();
    }

    public void handleCancellation() {
        // Placeholder for cancellation handling
        System.out.println("Handling cancellation.");
    }

    public void showSuccessMessage() {
        System.out.println("Preferences updated successfully!");
    }
}
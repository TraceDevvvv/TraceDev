package com.example.touristapp.controller;

import com.example.touristapp.dto.SearchPreferencesDTO;
import com.example.touristapp.service.AuthenticationService;
import com.example.touristapp.service.EditSearchPreferencesUseCase;
import com.example.touristapp.view.SearchPreferencesView;

import java.util.Map;

/**
 * Controller for handling search preferences related requests from the UI.
 * It mediates between the View and the Use Case layer.\n */
public class SearchPreferencesController {

    private EditSearchPreferencesUseCase editSearchPreferencesUseCase;
    private SearchPreferencesView searchPreferencesView;
    private AuthenticationService authenticationService;

    // A temporary holder for tourist ID for the current session/flow, could be managed by a session service.
    private String currentTouristId;
    // A temporary holder for preferences being edited, to pass to confirmation.
    private SearchPreferencesDTO pendingPreferencesDTO;

    /**
     * Constructor for SearchPreferencesController.\n     * @param editSearchPreferencesUseCase The use case for editing preferences.\n     * @param authenticationService The service for authentication checks.\n     */
    public SearchPreferencesController(EditSearchPreferencesUseCase editSearchPreferencesUseCase,
                                       AuthenticationService authenticationService) {
        this.editSearchPreferencesUseCase = editSearchPreferencesUseCase;
        this.authenticationService = authenticationService;
        // The view is associated later due to circular dependency (Controller needs View, View needs Controller).
    }

    // Setter for SearchPreferencesView to resolve circular dependency
    public void setSearchPreferencesView(SearchPreferencesView searchPreferencesView) {
        this.searchPreferencesView = searchPreferencesView;
    }

    /**
     * Handles the request to show the edit preferences form.\n     * This is the entry point from the View to initiate the editing process.\n     * @param touristId The ID of the tourist requesting to edit preferences.\n     */
    public void showEditPreferencesForm(String touristId) {
        System.out.println("\n--- Controller: Received request to show edit preferences form for tourist: " + touristId + " ---");
        this.currentTouristId = touristId; // Store for subsequent operations

        // 1. Authenticate the tourist
        if (!authenticationService.isAuthenticated(touristId)) {
            searchPreferencesView.showErrorNotification("Authentication failed for tourist ID: " + touristId);
            return;
        }

        // 2. Get existing preferences
        SearchPreferencesDTO preferencesDTO = editSearchPreferencesUseCase.getPreferences(touristId);

        // 3. Display preferences in the view
        if (preferencesDTO != null) {
            searchPreferencesView.displayPreferencesForm(preferencesDTO);
        } else {
            // If no preferences found, display empty form or a message.
            // For this demo, let's create a default DTO for initial display.
            SearchPreferencesDTO defaultPreferences = new SearchPreferencesDTO(touristId, "Anywhere", "Flexible", "Anytime");
            System.out.println("[SearchPreferencesController] No existing preferences found, displaying default form.");
            searchPreferencesView.displayPreferencesForm(defaultPreferences);
        }
    }

    /**
     * Handles the submission of edited preferences from the view.\n     * @param touristId The ID of the tourist.\n     * @param formData A map containing the edited form data (e.g., "destination" -> "New York").\n     */
    public void handleSubmit(String touristId, Map<String, String> formData) {
        System.out.println("\n--- Controller: Handling submitted preferences for tourist: " + touristId + " ---");
        // Update currentTouristId in case it changed or wasn't set (though showEditPreferencesForm should set it)
        this.currentTouristId = touristId;

        // Convert formData to SearchPreferencesDTO
        // This is where the implied conversion from sequence diagram happens.
        SearchPreferencesDTO editedPreferencesDTO = new SearchPreferencesDTO(
            touristId,
            formData.getOrDefault("destination", null), // null if not provided
            formData.getOrDefault("budgetRange", null),
            formData.getOrDefault("travelDates", null)
        );

        // We need to fetch the original preferences to intelligently merge,\n        // or just ensure the DTO has all fields filled for confirmation.
        // For simplicity, we'll assume formData contains *all* fields,\n        // even if user didn't change them, or we merge with existing.
        // The sequence diagram suggests `Controller converts formData to DTO (implied)`\n        // and then `Controller -> View: showConfirmation(editedPreferencesDTO)`.
        // So, the DTO passed to showConfirmation should be complete.
        SearchPreferencesDTO currentPreferences = editSearchPreferencesUseCase.getPreferences(touristId);
        if (currentPreferences == null) {
             currentPreferences = new SearchPreferencesDTO(touristId, "", "", ""); // Default empty
        }

        // Merge edited data with current data to create a full DTO for confirmation
        String finalDestination = formData.containsKey("destination") ? formData.get("destination") : currentPreferences.getDestination();
        String finalBudgetRange = formData.containsKey("budgetRange") ? formData.get("budgetRange") : currentPreferences.getBudgetRange();
        String finalTravelDates = formData.containsKey("travelDates") ? formData.get("travelDates") : currentPreferences.getTravelDates();

        this.pendingPreferencesDTO = new SearchPreferencesDTO(touristId, finalDestination, finalBudgetRange, finalTravelDates);

        // Show confirmation to the user via the view
        searchPreferencesView.showConfirmationPrompt(pendingPreferencesDTO);
    }

    /**
     * Handles the user's confirmation of the edited preferences.\n     * @param touristId The ID of the tourist.\n     * @param confirmedPreferences The DTO containing the preferences confirmed by the user.\n     */
    public void handleConfirm(String touristId, SearchPreferencesDTO confirmedPreferences) {
        System.out.println("\n--- Controller: Handling confirmed preferences for tourist: " + touristId + " ---");
        this.currentTouristId = touristId; // Update tourist ID

        try {
            // Delegate the update to the use case layer
            editSearchPreferencesUseCase.updatePreferences(touristId, confirmedPreferences);
            // On success, notify the view
            searchPreferencesView.showSuccessNotification();
        } catch (Exception e) {
            // On error, notify the view
            searchPreferencesView.showErrorNotification(e.getMessage());
        }
        // Clear pending DTO after operation
        this.pendingPreferencesDTO = null;
        this.currentTouristId = null;
    }

    /**
     * Handles the user's cancellation of the update operation.\n     */
    public void handleCancel() {
        System.out.println("\n--- Controller: Handling cancellation of preferences update ---");
        // Notify the view to display a cancellation message
        searchPreferencesView.displayCancellationMessage();
        // Clear pending DTO and tourist ID
        this.pendingPreferencesDTO = null;
        this.currentTouristId = null;
    }
}
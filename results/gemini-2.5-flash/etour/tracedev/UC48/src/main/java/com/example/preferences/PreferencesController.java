package com.example.preferences;

/**
 * Controller layer that handles requests related to user preferences.
 * It orchestrates interactions between the View and the Service layers.
 */
public class PreferencesController {

    private final PreferenceManagementService preferenceManagementService;
    private final PreferencesView preferencesView;
    private final AuthenticationService authenticationService;

    // A flag to simulate connection errors as per the sequence diagram
    private boolean simulateConnectionError = false;

    /**
     * Constructor for PreferencesController.
     * @param preferenceManagementService The service layer dependency.
     * @param preferencesView The view layer dependency.
     * @param authenticationService The authentication service dependency.
     */
    public PreferencesController(
            PreferenceManagementService preferenceManagementService,
            PreferencesView preferencesView,
            AuthenticationService authenticationService) {
        this.preferenceManagementService = preferenceManagementService;
        this.preferencesView = preferencesView;
        this.authenticationService = authenticationService;
    }

    /**
     * Handles the request to edit user preferences.
     * Corresponds to `requestEditPreferences(touristId)` in the sequence diagram.
     *
     * @param touristId The ID of the tourist whose preferences are to be edited.
     * @return A PreferencesViewModel containing the current preferences to display in the form.
     */
    public PreferencesViewModel requestEditPreferences(String touristId) {
        System.out.println("[Controller] Received request to edit preferences for tourist: " + touristId);
        // Note: touristId retrieved from authenticated session (simulated by AuthenticationService)
        String currentTouristId = authenticationService.getCurrentTouristId();
        if (!currentTouristId.equals(touristId)) {
            System.err.println("[Controller] Authentication mismatch for tourist ID: " + touristId);
            // In a real app, this would be an authorization error.
            return null;
        }

        // Delegate to service to get current preferences
        PreferenceDto currentPreferencesDto = preferenceManagementService.getPreferences(touristId);

        // Convert DTO to ViewModel for display
        System.out.println("[Controller] Converting PreferenceDto to PreferencesViewModel.");
        PreferencesViewModel viewModel = new PreferencesViewModel(
                currentPreferencesDto.getTouristId(),
                currentPreferencesDto.getTheme(),
                currentPreferencesDto.getLanguage(),
                currentPreferencesDto.getTimezone()
        );

        // Render the form via the view
        preferencesView.renderEditForm(viewModel);
        System.out.println("[Controller] Displayed edit form to tourist.");
        return viewModel;
    }

    /**
     * Handles the submission of edited preferences by the user.
     * Corresponds to `submitEditedPreferences(touristId, dto)` in the sequence diagram.
     * This method initiates the confirmation prompt.
     *
     * @param touristId The ID of the tourist.
     * @param dto The PreferenceDto containing the edited preference data.
     */
    public void submitEditedPreferences(String touristId, PreferenceDto dto) {
        System.out.println("[Controller] Received submission of edited preferences for tourist: " + touristId + ", data: " + dto);
        // Display confirmation prompt
        preferencesView.displayConfirmationPrompt();
        System.out.println("[Controller] Confirmation prompt displayed.");
    }

    /**
     * Handles the confirmation of saving edited preferences.
     * Corresponds to `handleConfirmation(touristId, dto)` in the sequence diagram.
     *
     * @param touristId The ID of the tourist.
     * @param dto The PreferenceDto containing the edited preference data.
     */
    public void handleConfirmation(String touristId, PreferenceDto dto) {
        System.out.println("[Controller] Handling confirmation for tourist: " + touristId);

        if (simulateConnectionError) {
            // Simulate connection interruption as per sequence diagram
            System.err.println("[Controller] Simulating connection interruption to ETOUR server.");
            preferencesView.displayErrorMessage("Connection to ETOUR interrupted. Please try again.");
            return;
        }

        // Delegate to service to update preferences
        boolean success = preferenceManagementService.updatePreferences(touristId, dto);

        if (success) {
            preferencesView.displaySuccessMessage("Preferences updated successfully.");
            System.out.println("[Controller] Preferences successfully updated and success message displayed.");
        } else {
            preferencesView.displayErrorMessage("Failed to update preferences. Please try again.");
            System.err.println("[Controller] Preference update failed, error message displayed.");
        }
    }

    /**
     * Handles the cancellation of the preference editing operation.
     * Corresponds to `handleCancellation()` in the sequence diagram.
     */
    public void handleCancellation() {
        System.out.println("[Controller] Handling cancellation.");
        preferencesView.displayCancellationMessage("Operation cancelled.");
        System.out.println("[Controller] Cancellation message displayed.");
    }

    /**
     * Sets the flag to simulate a connection error for testing purposes.
     * @param simulateConnectionError True to simulate error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }
}
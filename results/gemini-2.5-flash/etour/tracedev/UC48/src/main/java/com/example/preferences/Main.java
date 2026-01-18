package com.example.preferences;

/**
 * Main class to demonstrate the flow of the "Edit Generic Personal Preferences" use case.
 * This acts as the application entry point and sets up the dependencies.
 */
public class Main {
    public static void main(String[] args) {
        // --- Setup Dependencies ---
        PreferencesRepository preferencesRepository = new JpaPreferencesRepository();
        PreferenceManagementService preferenceManagementService = new PreferenceManagementService(preferencesRepository);
        PreferencesView preferencesView = new PreferencesView();
        AuthenticationService authenticationService = new AuthenticationService();

        PreferencesController preferencesController = new PreferencesController(
                preferenceManagementService,
                preferencesView,
                authenticationService
        );

        // --- Simulate the "Edit Generic Personal Preferences" Use Case ---
        System.out.println("--- Starting Edit Generic Personal Preferences Use Case Simulation ---");

        // Entry Condition: Tourist IS authenticated. (Handled by AuthenticationService default)
        String touristId = authenticationService.getCurrentTouristId();
        System.out.println("\n[Tourist] Tourist " + touristId + " is authenticated.");

        // 1. Tourist requests to edit preferences
        System.out.println("\n--- Step 1: Tourist requests to edit preferences ---");
        PreferencesViewModel currentViewModel = preferencesController.requestEditPreferences(touristId);

        // Simulate Tourist editing and submitting the form
        // For demonstration, we'll create a new DTO representing user input.
        System.out.println("\n--- Step 2: Tourist edits and submits the form ---");
        // Simulate user input for an updated preference.
        // In a real scenario, preferencesView.getUserInput() would gather this.
        PreferenceDto editedPreferenceDto = new PreferenceDto(
                touristId,
                "dark",       // Changed theme
                "es",         // Changed language
                "GMT-5"       // Changed timezone
        );
        System.out.println("[Tourist] Edited preferences: " + editedPreferenceDto);

        // PreferencesView collects user input (simulated)
        // PreferencesView -> PreferencesController : submitEditedPreferences(touristId, editedPreferenceDto)
        preferencesController.submitEditedPreferences(touristId, editedPreferenceDto);

        // PreferencesView --> Tourist : "Are you sure you want to save changes?"
        // This is handled by preferencesView.displayConfirmationPrompt() in the controller call

        // --- Scenario 1: Tourist confirms the operation (Successful update) ---
        System.out.println("\n--- Scenario 1: Tourist confirms the operation (Successful update) ---");
        System.out.println("[Tourist] Confirming changes...");
        // Tourist -> PreferencesView : confirmChange() (simulated)
        // preferencesView.getConfirmationInput() would return true
        if (true) { // Simulate 'y' input
            // PreferencesView -> PreferencesController : handleConfirmation(touristId, editedPreferenceDto)
            preferencesController.handleConfirmation(touristId, editedPreferenceDto);
            // Success message displayed by PreferencesView
        } else {
            // This path won't be taken in this simulation due to 'true' above.
            preferencesController.handleCancellation();
        }

        // Verify the update
        System.out.println("\n--- Verification of successful update ---");
        PreferenceDto updatedPreferences = preferenceManagementService.getPreferences(touristId);
        System.out.println("[Main] Preferences after successful update: " + updatedPreferences);
        if ("dark".equals(updatedPreferences.getTheme()) && "es".equals(updatedPreferences.getLanguage())) {
            System.out.println("[Main] Verification successful: Preferences were updated.");
        } else {
            System.err.println("[Main] Verification FAILED: Preferences were NOT updated as expected.");
        }


        // --- Scenario 2: Tourist confirms, but connection to server is interrupted ---
        System.out.println("\n\n--- Scenario 2: Tourist confirms, connection interrupted ---");
        System.out.println("[Tourist] Attempting to update preferences with simulated error...");
        // Reset to original for this scenario, or use a new DTO
        PreferenceDto reEditedPreferenceDto = new PreferenceDto(
                touristId,
                "light",      // Changed theme again
                "en",         // Changed language again
                "GMT+1"       // Changed timezone again
        );
        preferencesController.submitEditedPreferences(touristId, reEditedPreferenceDto);
        preferencesController.setSimulateConnectionError(true); // Enable error simulation

        System.out.println("[Tourist] Confirming changes with simulated connection error...");
        // Tourist -> PreferencesView : confirmChange() (simulated)
        // preferencesView.getConfirmationInput() would return true
        if (true) { // Simulate 'y' input
            // PreferencesView -> PreferencesController : handleConfirmation(touristId, reEditedPreferenceDto)
            preferencesController.handleConfirmation(touristId, reEditedPreferenceDto);
            // Error message displayed by PreferencesView
        }

        // Verify that preferences were NOT updated due to error
        System.out.println("\n--- Verification of failed update ---");
        PreferenceDto preferencesAfterError = preferenceManagementService.getPreferences(touristId);
        System.out.println("[Main] Preferences after simulated error: " + preferencesAfterError);
        if ("dark".equals(preferencesAfterError.getTheme()) && "es".equals(preferencesAfterError.getLanguage())) {
            System.out.println("[Main] Verification successful: Preferences were NOT updated (still 'dark', 'es').");
        } else {
            System.err.println("[Main] Verification FAILED: Preferences were unexpectedly updated or incorrect.");
        }
        preferencesController.setSimulateConnectionError(false); // Disable error simulation for next steps

        // --- Scenario 3: Tourist cancels the operation ---
        System.out.println("\n\n--- Scenario 3: Tourist cancels the operation ---");
        System.out.println("[Tourist] Requesting edit form again to demonstrate cancellation.");
        preferencesController.requestEditPreferences(touristId);

        PreferenceDto thirdEditedPreferenceDto = new PreferenceDto(
                touristId,
                "purple",     // New proposed theme
                "fr",         // New proposed language
                "CET"         // New proposed timezone
        );
        preferencesController.submitEditedPreferences(touristId, thirdEditedPreferenceDto);

        System.out.println("[Tourist] Cancelling changes...");
        // Tourist -> PreferencesView : cancelChange() (simulated)
        // preferencesView.getConfirmationInput() would return false
        if (false) { // Simulate 'n' input
            preferencesController.handleConfirmation(touristId, thirdEditedPreferenceDto);
        } else {
            // PreferencesView -> PreferencesController : handleCancellation()
            preferencesController.handleCancellation();
            // Cancellation message displayed by PreferencesView
        }

        // Verify that preferences were NOT updated due to cancellation
        System.out.println("\n--- Verification of cancelled update ---");
        PreferenceDto preferencesAfterCancellation = preferenceManagementService.getPreferences(touristId);
        System.out.println("[Main] Preferences after cancellation: " + preferencesAfterCancellation);
        if ("dark".equals(preferencesAfterCancellation.getTheme()) && "es".equals(preferencesAfterCancellation.getLanguage())) {
            System.out.println("[Main] Verification successful: Preferences were NOT updated (still 'dark', 'es').");
        } else {
            System.err.println("[Main] Verification FAILED: Preferences were unexpectedly updated or incorrect.");
        }

        System.out.println("\n--- End of Use Case Simulation ---");
    }
}
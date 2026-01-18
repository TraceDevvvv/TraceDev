package com.example.ui;

import com.example.dto.PreferenceSetDTO;
import com.example.controller.PreferenceController;

/**
 * Boundary class for editing preferences.
 */
public class EditPreferencesForm {
    private PreferenceController controller;

    public EditPreferencesForm(PreferenceController controller) {
        this.controller = controller;
    }

    // Added for consistency with sequence diagram message
    public void openPreferencesModification() {
        System.out.println("Form opened for preferences modification.");
    }

    public void displayForm(PreferenceSetDTO preferences) {
        System.out.println("Displaying form with current preferences:");
        System.out.println("Language: " + preferences.getLanguage());
        System.out.println("Theme: " + preferences.getTheme());
        System.out.println("Notifications enabled: " + preferences.isNotificationsEnabled());
        System.out.println("Accessibility options: " + preferences.getAccessibilityOptions());
    }

    public PreferenceSetDTO getUserInput() {
        // Simulate user input - in real scenario, this would come from UI components
        PreferenceSetDTO dto = new PreferenceSetDTO();
        dto.setLanguage("English");
        dto.setTheme("Dark");
        dto.setNotificationsEnabled(true);
        dto.setAccessibilityOptions(new java.util.HashMap<>());
        return dto;
    }

    public boolean showConfirmationDialog() {
        System.out.println("Show confirmation dialog: Are you sure you want to update preferences?");
        // Simulate user confirmation - assume true for simplicity
        return true;
    }

    public void showSuccessMessage() {
        System.out.println("Preferences updated successfully!");
    }

    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    // Missing sequence diagram messages
    public void displayFormWithCurrentPreferences() {
        // This method corresponds to m11: display form with current preferences
        System.out.println("Displaying form with current preferences populated.");
    }

    public void showErrorMessage() {
        // This method corresponds to m13: show error message
        System.out.println("Error occurred during preferences modification.");
    }

    public void terminateFlow() {
        // This method corresponds to m15: terminate flow
        System.out.println("Terminating flow due to error or interruption.");
    }

    public void showConfirmationDialogToTourist() {
        // This method corresponds to m21: show confirmation dialog (different context)
        System.out.println("Show confirmation dialog to tourist before proceeding.");
    }

    public void showCancellationMessage() {
        // This method corresponds to m34: show cancellation message
        System.out.println("Operation cancelled by tourist.");
    }
}
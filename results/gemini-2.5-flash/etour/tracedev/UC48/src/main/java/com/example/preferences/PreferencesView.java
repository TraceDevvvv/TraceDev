package com.example.preferences;

import java.util.Scanner;

/**
 * Represents the user interface component for preferences.
 * In a real application, this would be a UI framework component (e.g., Spring MVC View, React component).
 * For this simulation, it uses console output and input.
 */
public class PreferencesView {

    private Scanner scanner = new Scanner(System.in); // For simulating user input

    /**
     * Renders the edit form for preferences with pre-filled values from the ViewModel.
     * Corresponds to `renderEditForm(preferencesViewModel)` in the sequence diagram.
     *
     * @param viewModel The PreferencesViewModel containing the current preferences to display.
     */
    public void renderEditForm(PreferencesViewModel viewModel) {
        System.out.println("\n--- Preferences Edit Form ---");
        System.out.println("Tourist ID: " + viewModel.getTouristId());
        System.out.println("Current Theme: " + viewModel.getTheme());
        System.out.println("Current Language: " + viewModel.getLanguage());
        System.out.println("Current Timezone: " + viewModel.getTimezone());
        System.out.println("-----------------------------");
        System.out.println("Please enter new preferences or type 'cancel' for any field to keep current:");
        // In a real UI, this would render input fields pre-populated.
    }

    /**
     * Simulates getting user input for updated preferences.
     * Corresponds to `getUserInput()` in the sequence diagram.
     * For this example, it will gather all necessary fields to construct a PreferenceDto.
     *
     * @return A PreferenceDto with the simulated user input.
     */
    public PreferenceDto getUserInput() {
        System.out.println("[View] Simulating user input for new preferences...");
        System.out.print("Enter Tourist ID: "); // In a real app, this would be implicit from session
        String touristId = scanner.nextLine();
        System.out.print("Enter new Theme (e.g., 'dark', 'light'): ");
        String theme = scanner.nextLine();
        System.out.print("Enter new Language (e.g., 'en', 'es'): ");
        String language = scanner.nextLine();
        System.out.print("Enter new Timezone (e.g., 'GMT+1', 'America/New_York'): ");
        String timezone = scanner.nextLine();

        // Construct PreferenceDto from user input
        PreferenceDto dto = new PreferenceDto(touristId, theme, language, timezone);
        System.out.println("[View] User input collected: " + dto);
        return dto;
    }

    /**
     * Displays a confirmation prompt to the user.
     * Corresponds to `displayConfirmationPrompt()` in the sequence diagram.
     */
    public void displayConfirmationPrompt() {
        System.out.println("[View] Are you sure you want to save these changes? (y/n)");
        // In a real UI, this would be a modal dialog or confirmation button.
    }

    /**
     * Displays a success message to the user.
     * Corresponds to `displaySuccessMessage(message)` in the sequence diagram.
     *
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("[View] SUCCESS: " + message);
        // In a real UI, this would be a toast notification or a success page.
    }

    /**
     * Displays a cancellation message to the user.
     * Corresponds to `displayCancellationMessage(message)` in the sequence diagram.
     *
     * @param message The cancellation message to display.
     */
    public void displayCancellationMessage(String message) {
        System.out.println("[View] CANCELLED: " + message);
        // In a real UI, this would redirect back or show a transient message.
    }

    /**
     * Displays an error message to the user.
     * Corresponds to `displayErrorMessage(message)` in the sequence diagram.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("[View] ERROR: " + message);
        // In a real UI, this would be an error banner or dialog.
    }

    /**
     * Simulates user confirmation (e.g., pressing 'y' for yes, 'n' for no).
     * @return true for confirmation, false for cancellation.
     */
    public boolean getConfirmationInput() {
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }
}
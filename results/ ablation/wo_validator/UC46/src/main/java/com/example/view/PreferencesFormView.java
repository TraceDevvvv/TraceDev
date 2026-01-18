package com.example.view;

import com.example.dto.SearchPreferencesDTO;
import com.example.controller.SearchPreferencesController;
import java.util.Scanner;

/**
 * Represents the UI view for preferences modification.
 * Simulates user interactions as per sequence diagram.
 */
public class PreferencesFormView {
    private SearchPreferencesController controller;
    private Scanner scanner;

    public PreferencesFormView(SearchPreferencesController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the form with current preferences.
     */
    public void displayForm(SearchPreferencesDTO preferences) {
        System.out.println("=== Search Preferences Form ===");
        if (preferences != null) {
            System.out.println("Location: " + preferences.getLocationPreference());
            System.out.println("Activity Type: " + preferences.getActivityTypePreference());
            System.out.println("Budget Range: " + preferences.getBudgetRange());
            System.out.println("Travel Dates: " + preferences.getTravelDates());
        } else {
            System.out.println("No existing preferences.");
        }
        System.out.println("==============================");
    }

    /**
     * Collects user input from console to create a DTO.
     */
    public SearchPreferencesDTO collectUserInput() {
        System.out.println("Enter new preferences:");
        System.out.print("Location: ");
        String location = scanner.nextLine();
        System.out.print("Activity Type: ");
        String activity = scanner.nextLine();
        System.out.print("Budget Range: ");
        double budget = Double.parseDouble(scanner.nextLine());
        System.out.print("Travel Dates (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        // For simplicity, we assume date parsing works; in real app use SimpleDateFormat.
        java.util.Date travelDates = java.sql.Date.valueOf(dateStr);
        return new SearchPreferencesDTO(location, activity, budget, travelDates);
    }

    /**
     * Shows a confirmation dialog and returns user's choice.
     */
    public boolean showConfirmationDialog() {
        System.out.print("Confirm update? (yes/no): ");
        String response = scanner.nextLine();
        return "yes".equalsIgnoreCase(response);
    }

    /**
     * Displays a success message.
     */
    public void showSuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Displays an error message.
     */
    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Clears the form (simulated).
     */
    public void clearForm() {
        System.out.println("Form cleared.");
    }

    /**
     * Main interaction method simulating the sequence diagram flow.
     */
    public void startModification(String touristId) {
        System.out.println("Tourist accesses modification functionality.");
        // Step 1: displayPreferencesForm
        SearchPreferencesDTO currentPrefs = controller.displayPreferencesForm(touristId);
        displayForm(currentPrefs);

        // Step 2: collect user input
        SearchPreferencesDTO newPrefs = collectUserInput();

        // Step 3: submitPreferencesUpdate
        String validationResult = controller.submitPreferencesUpdate(touristId, newPrefs);
        if ("VALIDATION_SUCCESS".equals(validationResult)) {
            // Show confirmation dialog
            boolean confirm = showConfirmationDialog();
            if (confirm) {
                String updateResult = controller.confirmUpdate(touristId, true);
                showSuccessMessage(updateResult);
            } else {
                System.out.println("Update cancelled.");
            }
        } else {
            showErrorMessage(validationResult);
        }
    }
}
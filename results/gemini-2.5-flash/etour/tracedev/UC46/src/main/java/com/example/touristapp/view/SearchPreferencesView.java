package com.example.touristapp.view;

import com.example.touristapp.controller.SearchPreferencesController;
import com.example.touristapp.dto.SearchPreferencesDTO;
import com.example.touristapp.service.SystemNotifications;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents the UI view for managing search preferences.
 * This class is responsible for displaying information and collecting user input.\n */
public class SearchPreferencesView {

    private SearchPreferencesController controller;
    private SystemNotifications notifications;
    private Scanner scanner = new Scanner(System.in); // For simulating user input

    /**
     * Constructor for SearchPreferencesView.\n     * @param controller The controller to which UI events are delegated.\n     * @param notifications The system notification service.\n     */
    public SearchPreferencesView(SearchPreferencesController controller, SystemNotifications notifications) {
        this.controller = controller;
        this.notifications = notifications;
        // The controller will be set in a circular dependency scenario, handled after construction.
    }

    // Setter for controller to resolve circular dependency
    public void setController(SearchPreferencesController controller) {
        this.controller = controller;
    }

    /**
     * Initiates the process of editing preferences by requesting them from the controller.\n     * @param touristId The ID of the tourist.\n     */
    public void requestEditPreferences(String touristId) {
        System.out.println("\n--- View: Tourist requests to edit preferences ---");
        // Delegate to controller to fetch existing preferences
        controller.showEditPreferencesForm(touristId);
    }

    /**
     * Displays the current search preferences in a form-like manner.\n     * @param preferences The DTO containing the preferences to display.\n     */
    public void displayPreferencesForm(SearchPreferencesDTO preferences) {
        System.out.println("\n--- View: Displaying current preferences for editing ---");
        System.out.println("Current Preferences for Tourist ID: " + preferences.getTouristId());
        System.out.println("  Destination: " + preferences.getDestination());
        System.out.println("  Budget Range: " + preferences.getBudgetRange());
        System.out.println("  Travel Dates: " + preferences.getTravelDates());
        System.out.println("\nPlease enter new values or press Enter to keep current value:");
        // After displaying, it simulates the user editing and submitting.
        // This is a simplification; in a real UI, this would be a separate user action.
        submitEditedPreferences(getEditedPreferences(), preferences.getTouristId()); // Pass touristId
    }

    /**
     * Simulates getting edited preferences from the user (console input).\n     * @return A Map containing the edited form data.\n     */
    public Map<String, String> getEditedPreferences() {
        Map<String, String> formData = new HashMap<>();
        System.out.print("  New Destination (current: N/A if not shown, leave blank to keep): ");
        String destination = scanner.nextLine();
        if (!destination.isEmpty()) {
            formData.put("destination", destination);
        }

        System.out.print("  New Budget Range (current: N/A if not shown, leave blank to keep): ");
        String budgetRange = scanner.nextLine();
        if (!budgetRange.isEmpty()) {
            formData.put("budgetRange", budgetRange);
        }

        System.out.print("  New Travel Dates (current: N/A if not shown, leave blank to keep): ");
        String travelDates = scanner.nextLine();
        if (!travelDates.isEmpty()) {
            formData.put("travelDates", travelDates);
        }

        System.out.println("View: Collected edited preferences: " + formData);
        return formData;
    }

    /**
     * Submits the edited preferences form data to the controller.\n     * @param editedFormData A map of field names to new values.\n     * @param touristId The ID of the tourist whose preferences are being edited. (Added for clarity in usage)\n     */
    public void submitEditedPreferences(Map<String, String> editedFormData, String touristId) {
        System.out.println("\n--- View: User submits edited preferences ---");
        controller.handleSubmit(touristId, editedFormData);
    }

    /**
     * Simulates submitting edited preferences without specifying touristId initially (to match original seq diag call).\n     * The controller will typically keep track of the current tourist.\n     * This method is an overload for the specific sequence diagram flow.\n     * @param editedFormData A map of field names to new values.\n     */
    public void submitEditedPreferences(Map<String, String> editedFormData) {
        // In a real UI, the view might not know the touristId directly but rely on session.
        // For this demo, let's assume the controller can derive it or it's passed around implicitly.
        // We need a tourist ID for the controller. We'll use a placeholder or assume it's known contextually.
        // The Main class will pass the touristId to requestEditPreferences, which then passes it to handleSubmit.
        // For simplicity in this direct call, we'll assume the controller holds context or we re-pass it.
        // The sequence diagram calls `View -> Controller: handleSubmit(touristId, formData)`
        // This means `submitEditedPreferences` should ideally receive `touristId`.
        // To strictly follow the sequence diagram `Tourist -> View: submitEditedPreferences(editedFormData...)`,
        // and then `View -> Controller: handleSubmit(touristId, formData)`, the view *must* have the touristId.
        // We'll pass it from the `Main` class to `requestEditPreferences`, and then keep it as a private field if needed,
        // or ensure it's passed down the chain. Let's make `displayPreferences` pass the touristId to `submitEditedPreferences`
        // as a workaround for this simulation.
        // For now, I'll rely on the `displayPreferences` -> `submitEditedPreferences(editedFormData, touristId)`
        // flow that correctly passes the touristId.
        // This method will essentially be called with the touristId context from the previous display.
        System.err.println("WARNING: Calling submitEditedPreferences without touristId. This might lead to issues in a real context.");
        // The call from displayPreferences will be `submitEditedPreferences(getEditedPreferences(), preferences.getTouristId());`
        // So this simpler method might not be used directly in the flow but kept for completeness of original CD.
    }


    /**
     * Displays a confirmation prompt to the user with the new preferences.\n     * @param preferences The DTO containing the preferences to confirm.\n     */
    public void showConfirmationPrompt(SearchPreferencesDTO preferences) {
        System.out.println("\n--- View: Displaying confirmation prompt ---");
        System.out.println("Please confirm the following changes:");
        System.out.println("  New Destination: " + preferences.getDestination());
        System.out.println("  New Budget Range: " + preferences.getBudgetRange());
        System.out.println("  New Travel Dates: " + preferences.getTravelDates());
        System.out.print("Confirm update? (yes/no): ");
        String confirmation = scanner.nextLine();
        boolean confirmed = confirmation.equalsIgnoreCase("yes");

        // Delegate confirmation response to controller
        confirmUpdate(confirmed, preferences.getTouristId(), preferences); // Pass all required data
    }

    /**
     * Simulates the user confirming or canceling the update.\n     * @param confirmed True if the user confirmed, false otherwise.\n     * @param touristId The ID of the tourist. (Added for controller method signature)\n     * @param confirmedPreferences The preferences DTO that was confirmed. (Added for controller method signature)\n     */
    public void confirmUpdate(boolean confirmed, String touristId, SearchPreferencesDTO confirmedPreferences) {
        System.out.println("View: User " + (confirmed ? "confirmed" : "canceled") + " the update.");
        if (confirmed) {
            controller.handleConfirm(touristId, confirmedPreferences);
        } else {
            controller.handleCancel();
        }
    }

    /**
     * Displays a success notification using the SystemNotifications service.\n     */
    public void showSuccessNotification() {
        notifications.displaySuccess("Preferences updated successfully.");
    }

    /**
     * Displays an error notification using the SystemNotifications service.\n     * @param message The error message to display.\n     */
    public void showErrorNotification(String message) {
        notifications.displayError("Failed to update preferences: " + message);
    }

    /**
     * Displays a message indicating cancellation of the operation.\n     */
    public void displayCancellationMessage() {
        System.out.println("\n--- View: Preferences update canceled. ---");
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
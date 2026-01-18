
package com.example.absencejustification.presentation;

import com.example.absencejustification.application.AbsenceJustificationController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the user interface form for entering absence justification details.
 * This class handles user input, communicates with the controller, and displays feedback.
 */
public class AbsenceJustificationForm {
    private AbsenceJustificationController controller;
    private RegistryScreen registryScreen;
    private AbsenceListScreen absenceListScreen; // For canceling operation

    private LocalDate justificationDateInput; // Simulates user input field for date
    private String currentAbsenceId; // The ID of the absence currently being justified

    /**
     * Sets the AbsenceJustificationController instance for this form to interact with.
     * @param controller The controller instance.
     */
    public void setController(AbsenceJustificationController controller) {
        this.controller = Objects.requireNonNull(controller, "AbsenceJustificationController cannot be null.");
    }

    /**
     * Sets the RegistryScreen instance for navigation after a successful justification.
     * @param registryScreen The RegistryScreen instance.
     */
    public void setRegistryScreen(RegistryScreen registryScreen) {
        this.registryScreen = Objects.requireNonNull(registryScreen, "RegistryScreen cannot be null.");
    }

    /**
     * Sets the AbsenceListScreen instance for navigation upon cancellation.
     * @param absenceListScreen The AbsenceListScreen instance.
     */
    public void setAbsenceListScreen(AbsenceListScreen absenceListScreen) {
        this.absenceListScreen = Objects.requireNonNull(absenceListScreen, "AbsenceListScreen cannot be null.");
    }

    /**
     * Displays the justification form for a specific absence.
     * @param absenceId The ID of the absence to be justified.
     */
    public void show(String absenceId) {
        this.currentAbsenceId = absenceId;
        this.justificationDateInput = null; // Clear previous input
        System.out.println("\n--- Absence Justification Form ---");
        System.out.println("Justifying Absence ID: " + absenceId);
        System.out.println("Please enter justification details.");
        // JustForm --> Admin : displayForm(absenceId) (simulated by print statements)
    }

    /**
     * Simulates the administrator filling in justification data.
     * @param dateString The justification date as a string (e.g., "YYYY-MM-DD").
     */
    public void fillJustificationData(String dateString) {
        System.out.println("[AbsenceJustificationForm] Administrator filled justification date: " + dateString);
        try {
            this.justificationDateInput = LocalDate.parse(dateString);
        } catch (java.time.format.DateTimeParseException e) {
            System.err.println("[AbsenceJustificationForm] Invalid date format entered: " + dateString);
            this.justificationDateInput = null; // Mark as invalid
        }
    }

    /**
     * Retrieves the entered justification data as a map.
     * @return A map containing the justification data.
     */
    public Map<String, String> getJustificationData() {
        Map<String, String> data = new HashMap<>();
        if (justificationDateInput != null) {
            data.put("date", justificationDateInput.toString());
        }
        return data;
    }

    /**
     * Simulates the administrator clicking the "Save" button.
     * This triggers the justification recording process.
     */
    public void onSaveButtonClick() {
        System.out.println("[AbsenceJustificationForm] Administrator clicked Save.");
        if (currentAbsenceId == null) {
            displayErrorMessage("No absence selected for justification.");
            return;
        }
        // JustForm -> JustController : recordAbsenceJustification(absenceId, justificationData)
        String errorMessage = controller.recordAbsenceJustification(currentAbsenceId, getJustificationData());

        if (errorMessage == null) {
            System.out.println("[AbsenceJustificationForm] Justification saved successfully.");
            // JustForm -> RegScreen : navigateToRegistryScreen()
            navigateToRegistryScreen();
        } else {
            // JustForm --> Admin : displayErrorMessage(...)
            displayErrorMessage(errorMessage);
        }
    }

    /**
     * Displays an error message to the administrator.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("[AbsenceJustificationForm] ERROR: " + message);
        System.out.println("--- End Absence Justification Form (with error) ---\n");
    }

    /**
     * Navigates to the Registry Screen.
     */
    public void navigateToRegistryScreen() {
        System.out.println("[AbsenceJustificationForm] Navigating to Registry Screen...");
        if (registryScreen != null) {
            registryScreen.display();
        } else {
            System.err.println("[AbsenceJustificationForm] Error: RegistryScreen not set.");
        }
        System.out.println("--- End Absence Justification Form (success) ---\n");
    }

    /**
     * Cancels the current justification operation.
     * // Added to satisfy requirement R13 and sequence diagram 'cancelOperation' opt block.
     */
    public void cancelOperation() {
        System.out.println("[AbsenceJustificationForm] Administrator clicked Cancel. Operation cancelled.");
        // JustForm --> Admin : displayAbsenceListScreen()
        if (absenceListScreen != null) {
            absenceListScreen.displayAbsenceListScreen();
        } else {
            System.err.println("[AbsenceJustificationForm] Error: AbsenceListScreen not set for navigation back.");
        }
        System.out.println("--- End Absence Justification Form (cancelled) ---\n");
    }
}

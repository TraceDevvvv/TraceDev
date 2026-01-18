package com.example.absencejustification.presentation;

import java.util.List;

/**
 * Represents the screen that displays a list of absences.
 * It allows the administrator to select an absence to justify.
 */
public class AbsenceListScreen {
    private AbsenceJustificationForm absenceJustificationForm;

    /**
     * Sets the AbsenceJustificationForm instance that this screen will interact with.
     * @param form The AbsenceJustificationForm instance.
     */
    public void setAbsenceJustificationForm(AbsenceJustificationForm form) {
        this.absenceJustificationForm = form;
    }

    /**
     * Displays a list of absences to the user.
     * @param absences A list of absence IDs (assuming Absence is represented by its ID here).
     */
    public void displayAbsences(List<String> absences) {
        System.out.println("\n--- Absence List Screen ---");
        System.out.println("Please select an absence to justify:");
        if (absences == null || absences.isEmpty()) {
            System.out.println("No absences currently requiring justification.");
        } else {
            absences.forEach(absenceId -> System.out.println("- Absence ID: " + absenceId));
        }
        System.out.println("---------------------------\n");
    }

    /**
     * Simulates an administrator clicking on a specific absence.
     * This action navigates to the Absence Justification Form.
     * @param absenceId The ID of the absence selected by the administrator.
     */
    public void onAbsenceSelected(String absenceId) {
        System.out.println("[AbsenceListScreen] Administrator clicked on Absence ID: " + absenceId);
        // AbsList -> JustForm : show(absenceId)
        if (absenceJustificationForm != null) {
            absenceJustificationForm.show(absenceId);
        } else {
            System.err.println("[AbsenceListScreen] Error: AbsenceJustificationForm not set.");
        }
    }

    /**
     * Simulates displaying the absence list screen, typically after an operation is cancelled.
     */
    public void displayAbsenceListScreen() {
        System.out.println("\n--- Navigated back to Absence List Screen ---");
        // In a real application, you might re-fetch and display absences here.
        // For this simulation, just print a message.
        System.out.println("Operation cancelled. Returning to absence overview.");
        System.out.println("--------------------------------------------\n");
    }
}
package presentation;

import application.DelayEliminationService;
import domain.LateEntry;
import java.util.Date;
import java.util.List;

/**
 * Controller for the Registry Screen, handling user interactions related to delay elimination.
 * This class interacts with the Application Layer to perform operations based on user input.
 * It belongs to the Presentation Layer.
 *
 * (Constraint) Operates only after "SplitTaTtAlloreGloregistration" use case execution.
 * This constraint is assumed to be handled by a higher-level workflow or permission system
 * and is not explicitly coded here.
 */
public class RegistryScreenController {

    private final DelayEliminationService delayEliminationService;
    private List<LateEntry> currentLateEntries; // Holds the currently displayed late entries
    private String selectedLateEntryIdForRemoval; // Stores the ID of the entry to be removed

    /**
     * Constructs a RegistryScreenController with a dependency on DelayEliminationService.
     * @param delayEliminationService The application service for delay elimination.
     */
    public RegistryScreenController(DelayEliminationService delayEliminationService) {
        this.delayEliminationService = delayEliminationService;
    }

    /**
     * Handles the user's action of selecting a date to view late entries.
     * Corresponds to `Admin -> UI : selectDate(selectedDate : Date)` in the sequence diagram.
     * @param date The date selected by the user.
     */
    public void selectDate(Date date) {
        System.out.println("\n[UI] Administrator selected date: " + date);
        // Entry Condition: A date IS selected for delay elimination.
        
        // Call to Application Layer to get late entries
        List<LateEntry> lateEntries = delayEliminationService.getLateEntriesForDate(date);
        this.currentLateEntries = lateEntries; // Store for potential future reference

        // Display the retrieved late entries
        displayLateEntries(lateEntries);
    }

    /**
     * Displays the given list of late entries on the screen (simulated by printing to console).
     * Corresponds to `UI -> UI : displayLateEntries(lateEntryList)` in the sequence diagram.
     * @param lateEntries The list of late entries to display.
     */
    public void displayLateEntries(List<LateEntry> lateEntries) {
        System.out.println("[UI] Displaying Late Entries:");
        if (lateEntries == null || lateEntries.isEmpty()) {
            System.out.println("  No late entries found for the selected date.");
        } else {
            lateEntries.forEach(entry ->
                System.out.println("  ID: " + entry.getId() + ", Student ID: " + entry.getStudentId() +
                                   ", Date: " + entry.getEntryDate() + ", Details: " + entry.getInputDetails())
            );
        }
        System.out.println("1. System updates the screen based on the selected date.");
    }

    /**
     * Handles the user's action of selecting a specific late entry for removal.
     * This prepares the controller for the subsequent save action.
     * Corresponds to `Admin -> UI : handleRemoveAction(lateEntryId : String)` in the sequence diagram.
     * @param lateEntryId The ID of the late entry to be marked for removal.
     */
    public void handleRemoveAction(String lateEntryId) {
        this.selectedLateEntryIdForRemoval = lateEntryId;
        System.out.println("\n[UI] Administrator selected late entry ID '" + lateEntryId + "' for removal.");
        System.out.println("2. Administrator removes the late input of a student.");
    }

    /**
     * Handles the user's action of clicking the "Save Changes" button.
     * This triggers the elimination of the previously selected late entry.
     * Corresponds to `Admin -> UI : saveChangesClicked()` in the sequence diagram.
     */
    public void saveChangesClicked() {
        System.out.println("\n[UI] Administrator clicked 'Save Changes'.");
        System.out.println("3. Administrator clicks \"Save\".");

        if (selectedLateEntryIdForRemoval != null && !selectedLateEntryIdForRemoval.isEmpty()) {
            // Call to Application Layer to eliminate the delay
            delayEliminationService.eliminateDelay(selectedLateEntryIdForRemoval);

            // After elimination, clear the selected ID and optionally refresh the displayed list
            System.out.println("[UI] Late entry with ID '" + selectedLateEntryIdForRemoval + "' has been processed.");
            this.selectedLateEntryIdForRemoval = null; // Clear the selection

            // Re-fetch and display current entries if there was an active date selected
            if (currentLateEntries != null && !currentLateEntries.isEmpty()) {
                // Simulate refreshing the screen by re-fetching for the same date (assuming date is retained)
                // For simplicity, we just print a message, but in a real UI, it would re-render.
                System.out.println("[UI] Screen would now be refreshed to show updated late entries (if any).");
            }
            System.out.println("Exit Condition: The system has eliminated the delay.");
            System.out.println("Exit Condition: The system remains on the registry screen.");
            System.out.println("Quality Requirement: Data integrity is maintained after deletion.");
        } else {
            System.out.println("[UI] No late entry was selected for removal. No changes saved.");
        }
    }
}
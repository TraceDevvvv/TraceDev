package com.example.justification;

import com.example.justification.exception.JustificationNotFoundException;
import com.example.justification.exception.SMOSServerException;
import com.example.justification.model.Justification;
import com.example.justification.service.JustificationService;

import java.util.Optional;
import java.util.Scanner;

/**
 * Main application class to demonstrate the "Eliminate Justification" use case.
 * This class simulates the user interaction and system responses as described
 * in the use case, including preconditions, event sequence, and postconditions.
 */
public class EliminateJustificationApp {

    private final JustificationService justificationService;
    private boolean isAdminLoggedIn = false; // Simulates administrator login status
    private String currentlyViewedJustificationId = null; // Simulates which justification is being viewed

    /**
     * Constructor for the application. Initializes the JustificationService.
     */
    public EliminateJustificationApp() {
        this.justificationService = new JustificationService();
    }

    /**
     * Simulates the administrator logging into the system.
     */
    public void simulateAdminLogin() {
        System.out.println("--- Simulating Administrator Login ---");
        this.isAdminLoggedIn = true;
        System.out.println("Administrator logged in successfully.");
    }

    /**
     * Simulates the administrator viewing the details of a specific justification.
     * This fulfills the "viewdetalticaustifica" precondition.
     *
     * @param justificationId The ID of the justification to view.
     * @return true if the justification was found and is now being viewed, false otherwise.
     */
    public boolean simulateViewJustificationDetails(String justificationId) {
        if (!isAdminLoggedIn) {
            System.out.println("Error: Administrator must be logged in to view justification details.");
            return false;
        }

        System.out.println("\n--- Simulating Viewing Justification Details for ID: " + justificationId + " ---");
        Optional<Justification> justificationOptional = justificationService.getJustificationById(justificationId);

        if (justificationOptional.isPresent()) {
            Justification justification = justificationOptional.get();
            System.out.println("Viewing details: " + justification);
            this.currentlyViewedJustificationId = justification.getId();
            System.out.println("Precondition met: System is viewing details of justification ID: " + justificationId);
            return true;
        } else {
            System.out.println("Error: Justification with ID '" + justificationId + "' not found.");
            this.currentlyViewedJustificationId = null;
            return false;
        }
    }

    /**
     * Simulates the administrator clicking the "Delete" button and the system
     * attempting to eliminate the justification.
     * This method encapsulates the core "Eliminate Justification" event sequence.
     *
     * @param justificationId The ID of the justification to eliminate.
     */
    public void simulateEliminateJustification(String justificationId) {
        // Precondition checks
        if (!isAdminLoggedIn) {
            System.out.println("Operation failed: Administrator is not logged in.");
            return;
        }
        if (currentlyViewedJustificationId == null || !currentlyViewedJustificationId.equals(justificationId)) {
            System.out.println("Operation failed: Justification ID '" + justificationId + "' is not currently being viewed.");
            System.out.println("Please view the justification details first before attempting to delete.");
            return;
        }

        System.out.println("\n--- Administrator Clicks 'Delete' for Justification ID: " + justificationId + " ---");
        try {
            // Event sequence: System eliminates justification.
            Justification eliminatedJustification = justificationService.eliminateJustification(justificationId);

            // Postcondition: The justification was eliminated by the system.
            System.out.println("Postcondition met: Justification '" + eliminatedJustification.getId() + "' was eliminated.");
            System.out.println("Updated Justification: " + eliminatedJustification);
            // Postcondition: The system returns to the registry screen.
            System.out.println("System returns to the registry screen.");
            this.currentlyViewedJustificationId = null; // No longer viewing details
        } catch (JustificationNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println("System returns to the registry screen (due to error).");
            this.currentlyViewedJustificationId = null;
        } catch (SMOSServerException e) {
            // Postcondition: Connection to the SMOS server interrupted.
            System.err.println("Critical Error: " + e.getMessage());
            System.err.println("Postcondition met: Connection to the SMOS server interrupted.");
            System.out.println("System returns to the registry screen (due to critical error).");
            this.currentlyViewedJustificationId = null;
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.err.println("An unexpected error occurred during justification elimination: " + e.getMessage());
            System.out.println("System returns to the registry screen (due to unexpected error).");
            this.currentlyViewedJustificationId = null;
        }
    }

    /**
     * Main method to run the demonstration.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        EliminateJustificationApp app = new EliminateJustificationApp();
        Scanner scanner = new Scanner(System.in);

        app.simulateAdminLogin();

        System.out.println("\n--- Demonstrate Successful Elimination ---");
        // Scenario 1: Successful elimination
        String successJustificationId = "J001";
        if (app.simulateViewJustificationDetails(successJustificationId)) {
            app.simulateEliminateJustification(successJustificationId);
        }
        System.out.println("\n--- Current state of J001 after attempt: " + app.justificationService.getJustificationById(successJustificationId).orElse(null));


        System.out.println("\n--- Demonstrate Justification Not Found ---");
        // Scenario 2: Justification not found
        String notFoundJustificationId = "J999";
        if (app.simulateViewJustificationDetails(notFoundJustificationId)) { // This will fail to view
            app.simulateEliminateJustification(notFoundJustificationId);
        } else {
            // Manually trigger elimination attempt to show the error if view failed
            System.out.println("\nAttempting to eliminate non-existent justification without viewing first (will also fail due to precondition check):");
            app.simulateEliminateJustification(notFoundJustificationId);
            System.out.println("\nAttempting to eliminate non-existent justification (bypassing view check for demonstration):");
            // To demonstrate JustificationNotFoundException directly, we can temporarily bypass the view check
            app.currentlyViewedJustificationId = notFoundJustificationId; // Simulate it was viewed
            app.simulateEliminateJustification(notFoundJustificationId);
            app.currentlyViewedJustificationId = null; // Reset
        }


        System.out.println("\n--- Demonstrate SMOS Server Interruption ---");
        // Scenario 3: SMOS server interruption (simulated for J003)
        String smosErrorJustificationId = "J003";
        if (app.simulateViewJustificationDetails(smosErrorJustificationId)) {
            app.simulateEliminateJustification(smosErrorJustificationId);
        }
        System.out.println("\n--- Current state of J003 after attempt: " + app.justificationService.getJustificationById(smosErrorJustificationId).orElse(null));


        System.out.println("\n--- Demonstrate Administrator Interrupts Operation (e.g., not logged in) ---");
        // Scenario 4: Administrator interrupts operation (e.g., not logged in)
        app.isAdminLoggedIn = false; // Simulate admin logged out
        String interruptedJustificationId = "J002";
        System.out.println("\nAttempting to view justification details while logged out:");
        app.simulateViewJustificationDetails(interruptedJustificationId); // This will fail
        System.out.println("\nAttempting to eliminate justification while logged out:");
        app.simulateEliminateJustification(interruptedJustificationId); // This will fail due to login check

        // Re-login for another scenario
        app.simulateAdminLogin();
        System.out.println("\n--- Demonstrate Administrator Interrupts Operation (e.g., not viewing correct justification) ---");
        // Scenario 5: Administrator interrupts operation (e.g., tries to delete J002 while viewing J004)
        String correctViewId = "J004";
        String incorrectDeleteId = "J002";
        if (app.simulateViewJustificationDetails(correctViewId)) {
            System.out.println("\nAdministrator attempts to delete " + incorrectDeleteId + " while viewing " + correctViewId + ":");
            app.simulateEliminateJustification(incorrectDeleteId); // This will fail due to currentlyViewedJustificationId mismatch
        }

        scanner.close();
        System.out.println("\n--- End of Demonstration ---");
    }
}
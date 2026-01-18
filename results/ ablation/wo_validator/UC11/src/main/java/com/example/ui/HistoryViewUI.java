
package com.example.ui;

import com.example.controller.ViewHistoryController;
import com.example.entities.Convention;
import java.util.List;

/**
 * Boundary class for UI as per class diagram.
 * Handles user interaction and displays results/errors.
 */
public class HistoryViewUI {
    private String selectedRestaurantId;
    private ViewHistoryController controller;

    /**
     * Constructor; initializes controller with repository.
     * In a real application, dependency injection would be used.
     */
    public HistoryViewUI() {
        // Hardcoded URL for simplicity; in real app would be configurable
        com.example.repository.EtourServerRepository repo = new com.example.repository.EtourServerRepository("http://etour.example.com");
        this.controller = new ViewHistoryController(repo);
    }

    /**
     * Handles restaurant selection as per sequence diagram.
     * AO -> UI : handleRestaurantSelection(restaurantId)
     */
    public void handleRestaurantSelection(String restaurantId) {
        this.selectedRestaurantId = restaurantId;
        try {
            // UI -> VC : retrieveConventionHistory(restaurantId)
            List<Convention> history = controller.retrieveConventionHistory(restaurantId);
            // UI -> UI : formatForDisplay()
            formatForDisplay();
            // UI -> AO : displayHistory(List<Convention>)
            displayHistory(history);
        } catch (Exception e) {
            // Alternative flow: connection interrupted
            handleError();
            displayError("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Displays the convention history list.
     * As per class diagram and sequence diagram.
     */
    public void displayHistory(List<Convention> history) {
        System.out.println("=== Convention History for Restaurant " + selectedRestaurantId + " ===");
        if (history.isEmpty()) {
            System.out.println("No conventions found.");
        } else {
            for (Convention conv : history) {
                System.out.println(conv);
            }
        }
        System.out.println("=============================================");
    }

    /**
     * Displays an error message.
     * As per class diagram and sequence diagram alternative flow.
     */
    public void displayError(String message) {
        System.err.println("ERROR: " + message);
    }

    /**
     * Simulates formatting data for display.
     * As per sequence diagram: UI -> UI : formatForDisplay()
     */
    private void formatForDisplay() {
        // In a real UI, would transform data for presentation
    }

    /**
     * Simulates error handling.
     * As per sequence diagram: UI -> UI : handleError()
     */
    private void handleError() {
        // Could log errors, reset states, etc.
    }
}

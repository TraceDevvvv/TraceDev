package com.example.actors;

import com.example.ui.HistoryViewUI;

/**
 * Represents the agency operator actor.
 * Calls the UI to view convention history.
 */
public class AgencyOperator {
    private HistoryViewUI ui;

    public AgencyOperator() {
        this.ui = new HistoryViewUI();
    }

    /**
     * Public method as per class diagram.
     * Initiates the view convention history use case.
     */
    public void viewConventionHistory(String restaurantId) {
        // As per sequence diagram: AO -> UI : handleRestaurantSelection(restaurantId)
        ui.handleRestaurantSelection(restaurantId);
    }

    /**
     * Main method to simulate the runnable scenario.
     */
    public static void main(String[] args) {
        AgencyOperator operator = new AgencyOperator();
        System.out.println("=== Scenario 1: Main Success Scenario ===");
        operator.viewConventionHistory("REST001");

        System.out.println("\n=== Scenario 2: Alternative Flow (Connection Interrupted) ===");
        // To simulate connection failure, we'd need to adjust repository logic.
        // For demonstration, we'll call again; failure may occur randomly.
        operator.viewConventionHistory("REST002");
    }
}
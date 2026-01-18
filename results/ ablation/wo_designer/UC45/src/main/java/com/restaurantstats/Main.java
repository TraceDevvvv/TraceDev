package com.restaurantstats;

/**
 * Main entry point for the statistics display application.
 * This class represents the system that the Point Of Restaurants Operator uses.
 */
public class Main {

    /**
     * Simulates the authentication and initiation of the statistics display.
     * Entry Condition: Point Of Restaurants Operator IS authenticated to the system.
     */
    public static void main(String[] args) {
        // In a real application, authentication would happen here.
        // For this demo, we assume authentication is already successful.

        System.out.println("Point Of Restaurants Operator authenticated.");

        // Step 1: Operator selects the feature to display personal statistics.
        System.out.println("Operator selects personal statistics feature.");

        // The system creates and displays the statistics form.
        // The refreshment point ID would be obtained from the operator's session.
        String refreshmentPointId = "RP-2024-001"; // Example ID from session

        // Launch the Swing form on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            StatisticsForm form = new StatisticsForm(refreshmentPointId);
            form.setVisible(true);
        });

        System.out.println("System ready. Statistics form displayed.");
    }
}
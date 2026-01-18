// Main class to run the application
package com.etour.agency;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Entry Condition: Agency Operator IS logged in
        AgencyOperator operator = new AgencyOperator("op1");

        // Simulate server connection check
        boolean serverConnected = checkServerConnection();
        if (!serverConnected) {
            System.err.println("Error: Interruption of the connection to the server ETOUR occurs.");
            return;
        }

        PointOfRestDAO dao = new PointOfRestDAO();

        // Start the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            PointOfRestListFrame listFrame = new PointOfRestListFrame(dao, operator);
            listFrame.setVisible(true);
        });
    }

    private static boolean checkServerConnection() {
        // Simulated server connection check
        // In a real application, this would ping the ETOUR server
        return true; // Assume connected for this demo
    }
}
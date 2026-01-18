package com.etour;

import com.etour.controller.PointOfRestController;
import com.etour.view.RefreshmentPointView;

/**
 * Main class to run the application and simulate the sequence diagram
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting ETOUR Refreshment Point System ===\n");

        // Create controller (which creates repository, session manager, and view)
        PointOfRestController controller = new PointOfRestController();
        RefreshmentPointView view = controller.getView();

        // Simulate the sequence diagram steps

        // 1. Entry Condition Check (via controller internally)

        // 2. Actor requests to display refreshment points (Flow of Events 1)
        System.out.println("1. Agency Operator requests to display refreshment points:");
        view.displayRefreshmentPoints();

        // 3. Actor selects a point (Flow of Events 2)
        System.out.println("\n2. Agency Operator selects point 'RP002':");
        view.onPointSelected("RP002");

        // Try another point that might trigger connection error (20% chance)
        System.out.println("\n3. Agency Operator selects another point 'RP001':");
        view.onPointSelected("RP001");

        // Try non-existent point
        System.out.println("\n4. Agency Operator selects non-existent point 'RP999':");
        view.onPointSelected("RP999");

        System.out.println("\n=== Simulation Complete ===");
    }
}
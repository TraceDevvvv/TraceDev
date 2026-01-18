package com.etour;

import com.etour.controller.PreferenceController;

/**
 * Main application to run the use case.
 */
public class MainApp {
    public static void main(String[] args) {
        PreferenceController controller = new PreferenceController();

        System.out.println("=== ETOUR Personal Preferences Modification ===\n");
        // Simulate authentication
        controller.authenticate();

        // Run the modification flow
        controller.modifyPreferences();

        controller.close();
        System.out.println("\nApplication terminated.");
    }
}
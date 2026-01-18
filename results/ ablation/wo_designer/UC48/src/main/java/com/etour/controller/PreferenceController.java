package com.etour.controller;

import com.etour.model.UserPreference;
import com.etour.service.PreferenceService;

import java.util.Scanner;

/**
 * Controller for managing the flow of preference modification.
 */
public class PreferenceController {
    private PreferenceService preferenceService;
    private Scanner scanner;
    private boolean isAuthenticated;

    public PreferenceController() {
        this.preferenceService = new PreferenceService();
        this.scanner = new Scanner(System.in);
        this.isAuthenticated = false;
    }

    /**
     * Simulates authentication (for demonstration purposes).
     */
    public void authenticate() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Simple authentication simulation
        isAuthenticated = username.equals("tourist") && password.equals("password");
        if (isAuthenticated) {
            System.out.println("Authentication successful.");
        } else {
            System.out.println("Authentication failed.");
        }
    }

    /**
     * Main flow for modifying generic personal preferences.
     */
    public void modifyPreferences() {
        // Entry condition: Tourist must be authenticated
        if (!isAuthenticated) {
            System.out.println("Error: Tourist is not authenticated.");
            return;
        }

        // 1. Tourist accesses the functionality
        System.out.println("\n--- Personal Preferences Modification ---");

        // Check server connection (exit condition)
        if (!preferenceService.isServerConnected()) {
            System.out.println("Error: Connection to server ETOUR is interrupted.");
            return;
        }

        // 2. System uploads preferences and the general view in a form
        UserPreference currentPref = preferenceService.loadPreferences();
        System.out.println("Current preferences loaded.");
        displayPreferences(currentPref);

        // 3. Tourist edits the fields in the form
        UserPreference newPref = editPreferences(currentPref);

        // 4. Tourist submits the form
        System.out.println("\nSubmitting form...");

        // 5. System asks for confirmation of the change
        System.out.print("Confirm changes? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("yes")) {
            // Exit condition: Tourist cancels the operation
            System.out.println("Operation cancelled by Tourist.");
            return;
        }

        // 6. Tourist confirms the operation (already done)

        // 7. System stores the changed preferences
        long startTime = System.currentTimeMillis();
        boolean success = preferenceService.updatePreferences(newPref);
        long endTime = System.currentTimeMillis();

        // Quality requirement: process within 5 seconds
        long duration = endTime - startTime;
        if (duration > 5000) {
            System.out.println("Warning: Process took longer than 5 seconds.");
        }

        if (success) {
            // Exit condition: successful modification
            System.out.println("Success: General preferences modified successfully.");
            System.out.println("Process completed in " + duration + " ms.");
            displayPreferences(newPref);
        } else {
            System.out.println("Error: Failed to update preferences (validation error or server issue).");
        }
    }

    /**
     * Displays the preferences in a form-like manner.
     */
    private void displayPreferences(UserPreference pref) {
        System.out.println("Form:");
        System.out.println("1. Language: " + pref.getLanguage());
        System.out.println("2. Theme: " + pref.getTheme());
        System.out.println("3. Email Notifications: " + (pref.isEmailNotifications() ? "Enabled" : "Disabled"));
        System.out.println("4. Currency: " + pref.getCurrency());
        System.out.println("5. Search Radius: " + pref.getSearchRadius() + " km");
    }

    /**
     * Allows the Tourist to edit each preference field.
     * @param current the current preference
     * @return the updated preference
     */
    private UserPreference editPreferences(UserPreference current) {
        UserPreference updated = new UserPreference();
        updated.setLanguage(promptField("Language", current.getLanguage()));
        updated.setTheme(promptField("Theme", current.getTheme()));
        updated.setEmailNotifications(promptBoolean("Email Notifications (true/false)", current.isEmailNotifications()));
        updated.setCurrency(promptField("Currency", current.getCurrency()));
        updated.setSearchRadius(promptInt("Search Radius (km)", current.getSearchRadius()));
        return updated;
    }

    private String promptField(String fieldName, String currentValue) {
        System.out.print(fieldName + " [" + currentValue + "]: ");
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? currentValue : input;
    }

    private boolean promptBoolean(String fieldName, boolean currentValue) {
        System.out.print(fieldName + " [" + currentValue + "]: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return currentValue;
        return Boolean.parseBoolean(input);
    }

    private int promptInt(String fieldName, int currentValue) {
        while (true) {
            System.out.print(fieldName + " [" + currentValue + "]: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) return currentValue;
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter an integer.");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
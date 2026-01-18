package com.example.touristapp;

import java.util.Optional;
import java.util.Scanner;

/**
 * Main application class to demonstrate the ModifySearchPreference use case.
 * This class simulates the user interface and interaction flow for a Tourist
 * to modify their search preferences.
 */
public class ModifySearchPreferenceApp {

    private final SearchPreferenceService preferenceService;
    private Tourist currentTourist;
    private Scanner scanner;

    /**
     * Constructs the ModifySearchPreferenceApp.
     * Initializes the SearchPreferenceService and a Scanner for user input.
     */
    public ModifySearchPreferenceApp() {
        this.preferenceService = new SearchPreferenceService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the authentication process for a tourist.
     * For this example, it just creates an authenticated Tourist object.
     * In a real application, this would involve actual login logic.
     *
     * @param username The username of the tourist.
     * @return An authenticated Tourist object.
     */
    public Tourist authenticateTourist(String username) {
        // Entry condition: The Tourist has successfully authenticated to the system.
        System.out.println("Simulating authentication for user: " + username);
        this.currentTourist = new Tourist(username, true);
        System.out.println("Tourist '" + username + "' authenticated successfully.");
        return currentTourist;
    }

    /**
     * Displays the current search preferences to the user in a form-like manner.
     *
     * @param preferences The SearchPreferences object to display.
     */
    private void displayPreferencesForm(SearchPreferences preferences) {
        System.out.println("\n--- Current Search Preferences ---");
        System.out.println("1. Preferred Destination: " + preferences.getPreferredDestination());
        System.out.println("2. Minimum Price: $" + String.format("%.2f", preferences.getMinPrice()));
        System.out.println("3. Maximum Price: $" + String.format("%.2f", preferences.getMaxPrice()));
        System.out.println("4. Number of Travelers: " + preferences.getNumberOfTravelers());
        System.out.println("5. Preferred Travel Dates: " + preferences.getPreferredTravelDates());
        System.out.println("----------------------------------");
    }

    /**
     * Allows the user to edit fields in the search preferences form.
     *
     * @param currentPreferences The preferences to be edited.
     * @return A new SearchPreferences object with the edited values, or the original if no changes.
     */
    private SearchPreferences editPreferences(SearchPreferences currentPreferences) {
        SearchPreferences editedPreferences = new SearchPreferences(
                currentPreferences.getPreferredDestination(),
                currentPreferences.getMinPrice(),
                currentPreferences.getMaxPrice(),
                currentPreferences.getNumberOfTravelers(),
                currentPreferences.getPreferredTravelDates()
        );

        System.out.println("\n--- Edit Search Preferences (Enter new value or press Enter to keep current) ---");

        System.out.print("Preferred Destination (" + currentPreferences.getPreferredDestination() + "): ");
        String input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            editedPreferences.setPreferredDestination(input.trim());
        }

        System.out.print("Minimum Price ($" + String.format("%.2f", currentPreferences.getMinPrice()) + "): ");
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            try {
                double minPrice = Double.parseDouble(input.trim());
                editedPreferences.setMinPrice(minPrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for minimum price. Keeping current value.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Keeping current value.");
            }
        }

        System.out.print("Maximum Price ($" + String.format("%.2f", currentPreferences.getMaxPrice()) + "): ");
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            try {
                double maxPrice = Double.parseDouble(input.trim());
                editedPreferences.setMaxPrice(maxPrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for maximum price. Keeping current value.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Keeping current value.");
            }
        }

        System.out.print("Number of Travelers (" + currentPreferences.getNumberOfTravelers() + "): ");
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            try {
                int numTravelers = Integer.parseInt(input.trim());
                editedPreferences.setNumberOfTravelers(numTravelers);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for number of travelers. Keeping current value.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Keeping current value.");
            }
        }

        System.out.print("Preferred Travel Dates (" + currentPreferences.getPreferredTravelDates() + "): ");
        input = scanner.nextLine();
        if (!input.trim().isEmpty()) {
            editedPreferences.setPreferredTravelDates(input.trim());
        }

        return editedPreferences;
    }

    /**
     * Main flow for modifying search preferences.
     * This method orchestrates the steps described in the use case.
     */
    public void modifyPreferencesFlow() {
        if (currentTourist == null || !currentTourist.isAuthenticated()) {
            System.out.println("Error: Tourist not authenticated. Please authenticate first.");
            return;
        }

        System.out.println("\n--- Accessing functionality for modification of personal search preferences ---");

        // 2. Upload your search preferences and displays them in a form.
        Optional<SearchPreferences> loadedPreferences = preferenceService.loadPreferences(currentTourist);
        SearchPreferences currentPreferences = loadedPreferences.orElseGet(SearchPreferences::new); // Use default if none found

        System.out.println("Current preferences loaded:");
        displayPreferencesForm(currentPreferences);

        // 3. Edit the fields in the form and submit.
        SearchPreferences newPreferences = editPreferences(currentPreferences);

        // Check if any changes were made
        if (newPreferences.equals(currentPreferences)) {
            System.out.println("\nNo changes detected. Operation cancelled.");
            return; // Tourist cancels the operation (implicitly by not changing anything)
        }

        System.out.println("\n--- Proposed Changes ---");
        displayPreferencesForm(newPreferences);

        // 4. Asks confirmation.
        System.out.print("Confirm changes? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (!confirmation.equalsIgnoreCase("yes")) {
            // Exit condition: The Tourist cancels the operation.
            System.out.println("Modification cancelled by user.");
            return;
        }

        // 5. Confirmation for changing search preferences.
        // 6. Memorize search preferences changed.
        try {
            // Simulate a potential connection interruption
            if (Math.random() < 0.1) { // 10% chance of connection interruption
                preferenceService.simulateConnectionInterruption();
                // Exit condition: Interruption of the connection to the server ETOUR.
                System.out.println("Failed to save preferences due to connection interruption. Please try again.");
                return;
            }

            boolean success = preferenceService.updatePreferences(currentTourist, newPreferences);
            if (success) {
                // Exit condition: The system shall notify the successful modification of search preferences.
                System.out.println("\nSearch preferences successfully modified and saved!");
            } else {
                System.out.println("\nFailed to modify search preferences. Please try again.");
            }
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Error modifying preferences: " + e.getMessage());
        }
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ModifySearchPreferenceApp app = new ModifySearchPreferenceApp();

        // Simulate a tourist logging in
        Tourist tourist = app.authenticateTourist("testUser");

        // Run the preference modification flow
        app.modifyPreferencesFlow();

        // Demonstrate with another user who might not have preferences yet
        System.out.println("\n--- Demonstrating for a new user ---");
        Tourist newUser = app.authenticateTourist("newUser");
        app.modifyPreferencesFlow();

        app.scanner.close();
    }
}
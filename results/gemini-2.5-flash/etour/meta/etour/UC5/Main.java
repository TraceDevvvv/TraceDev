// Main.java
package com.example.culturalheritage;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main class to demonstrate the "ViewCulturalHeritageCard" use case.
 * This class simulates the user interaction flow as described in the use case.
 */
public class Main {

    public static void main(String[] args) {
        // Initialize necessary components
        CulturalHeritageService heritageService = new CulturalHeritageService();
        AgencyOperator operator = new AgencyOperator("AgencyUser1");
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Starting ViewCulturalHeritageCard Use Case Demonstration ---");

        // Entry condition: The agency has logged in.
        System.out.println("\nAttempting to log in the agency operator...");
        operator.login();
        if (!operator.isLoggedIn()) {
            System.out.println("Operator failed to log in. Exiting demonstration.");
            scanner.close();
            return;
        }
        System.out.println("Operator " + operator.getUsername() + " is logged in: " + operator.isLoggedIn());

        // Flow of events User System:
        // 1. View the list of cultural goods as a result of the use case SearchCulturalHeritage,
        //    it selects and activates a function to view the card.
        System.out.println("\n--- Step 1: Search and Select Cultural Heritage ---");
        Map<String, String> availableHeritage = heritageService.searchCulturalHeritage();

        if (availableHeritage.isEmpty()) {
            System.out.println("No cultural heritage items found. Exiting demonstration.");
            scanner.close();
            return;
        }

        String selectedHeritageId = null;
        while (selectedHeritageId == null) {
            System.out.print("Please enter the ID of the cultural heritage you wish to view (e.g., CH001): ");
            String input = scanner.nextLine().trim();
            if (availableHeritage.containsKey(input)) {
                selectedHeritageId = input;
            } else {
                System.out.println("Invalid ID. Please choose from the available IDs.");
            }
        }
        System.out.println("Selected Cultural Heritage ID: " + selectedHeritageId);

        // 2 Loads the data for the selected cultural.
        System.out.println("\n--- Step 2: Load and Display Cultural Heritage Card (Normal Flow) ---");
        Optional<CulturalHeritage> culturalHeritageCard = heritageService.viewCulturalHeritageCard(operator, selectedHeritageId);

        // Exit conditions: The system displays the details of the selected cultural.
        if (culturalHeritageCard.isPresent()) {
            System.out.println("\n--- Cultural Heritage Card Details ---");
            System.out.println(culturalHeritageCard.get());
            System.out.println("------------------------------------");
        } else {
            System.out.println("\nFailed to display cultural heritage card details.");
        }

        // Demonstrate edge case: Interruption of the connection to the server ETOUR.
        System.out.println("\n--- Demonstrating Edge Case: ETOUR Server Connection Interruption ---");
        heritageService.setEtourServerConnected(false); // Simulate connection loss

        System.out.println("\nAttempting to view the same cultural heritage card with server disconnected...");
        Optional<CulturalHeritage> culturalHeritageCardDisconnected = heritageService.viewCulturalHeritageCard(operator, selectedHeritageId);

        if (culturalHeritageCardDisconnected.isPresent()) {
            System.out.println("\n--- Cultural Heritage Card Details (Unexpectedly displayed) ---");
            System.out.println(culturalHeritageCardDisconnected.get());
            System.out.println("------------------------------------");
        } else {
            System.out.println("\nAs expected, failed to display cultural heritage card details due to server interruption.");
        }

        // Demonstrate edge case: Operator not logged in.
        System.out.println("\n--- Demonstrating Edge Case: Operator Not Logged In ---");
        operator.logout(); // Log out the operator
        heritageService.setEtourServerConnected(true); // Restore server connection for this test

        System.out.println("\nAttempting to view a cultural heritage card with operator logged out...");
        Optional<CulturalHeritage> culturalHeritageCardLoggedOut = heritageService.viewCulturalHeritageCard(operator, "CH001"); // Using a known ID

        if (culturalHeritageCardLoggedOut.isPresent()) {
            System.out.println("\n--- Cultural Heritage Card Details (Unexpectedly displayed) ---");
            System.out.println(culturalHeritageCardLoggedOut.get());
            System.out.println("------------------------------------");
        } else {
            System.out.println("\nAs expected, failed to display cultural heritage card details because operator is not logged in.");
        }
        
        System.out.println("\n--- ViewCulturalHeritageCard Use Case Demonstration Finished ---");
        scanner.close();
    }
}
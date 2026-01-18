package com.tour.controllers;

import com.tour.models.Tourist;
import com.tour.serv.TouristService;
import com.tour.ui.TouristCardDisplay;
import java.util.List;
import java.util.Scanner;

/**
 * Controller that coordinates the flow of viewing Tourist details.
 */
public class TouristController {
    private TouristService touristService;
    private TouristCardDisplay touristCardDisplay;
    private Scanner scanner;

    public TouristController() {
        touristService = new TouristService();
        touristCardDisplay = new TouristCardDisplay();
        scanner = new Scanner(System.in);
    }

    /**
     * Main flow for the use case: search, list, select, and display tourist.
     * Assumes Agency Operator is already logged in (Entry Condition).
     */
    public void runUseCase() {
        System.out.println("Agency Operator logged in.");
        System.out.println("Activating SearchTourist use case...\n");

        // Step 1 & 2: Obtain list of Tourists
        List<Tourist> tourists = touristService.searchTourists();
        if (tourists.isEmpty()) {
            System.out.println("No tourists found.");
            return;
        }

        // Display list
        System.out.println("List of Tourists:");
        for (int i = 0; i < tourists.size(); i++) {
            Tourist t = tourists.get(i);
            System.out.println((i + 1) + ". " + t.getName() + " (ID: " + t.getId() + ")");
        }

        // Step 3: Agency Operator selects a Tourist
        Tourist selected = selectTourist(tourists);
        if (selected == null) {
            System.out.println("Invalid selection or operation cancelled.");
            return;
        }

        // Step 4, 5, 6: Activate display function, upload data, display card
        displayTouristCard(selected);
    }

    /**
     * Prompts the user to select a Tourist from the list.
     */
    private Tourist selectTourist(List<Tourist> tourists) {
        System.out.print("\nEnter the number of the Tourist to view details (or 0 to cancel): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) {
                return null;
            }
            if (choice > 0 && choice <= tourists.size()) {
                return tourists.get(choice - 1);
            } else {
                System.out.println("Invalid number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        return null;
    }

    /**
     * Retrieves fresh data and displays the Tourist card.
     * Handles server interruption scenario.
     */
    private void displayTouristCard(Tourist selected) {
        // Simulate uploading data from server (could throw interruption)
        try {
            Tourist freshData = touristService.getTouristById(selected.getId());
            if (freshData == null) {
                // This should not happen with consistent mock data, but handles edge case
                System.out.println("Tourist data could not be retrieved.");
                return;
            }
            touristCardDisplay.displayTouristCard(freshData);
        } catch (Exception e) {
            // Simulating server interruption (ETOUR connection lost)
            System.out.println("ERROR: Connection to server ETOUR interrupted.");
            System.out.println("Exit condition: Connection interrupted.");
            System.exit(1); // or handle more gracefully in a real app
        }
    }

    public void close() {
        scanner.close();
    }
}
package com.example.ui;

import com.example.dto.PointOfRestDTO;
import com.example.dto.ConventionHistoryDTO;
import com.example.controller.HistoryController;
import com.example.exception.ConnectionException;
import java.util.List;
import java.util.Scanner;

/**
 * Boundary class representing the user interface for agency operators.
 * Simulates user interactions as per sequence diagram.
 */
public class AgencyOperatorUI {
    private HistoryController historyController;
    private Scanner scanner;

    public AgencyOperatorUI(HistoryController controller) {
        this.historyController = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display the history of conventions feature.
     * This method simulates the flow described in the sequence diagram.
     */
    public void displayHistoryOfConventions() {
        System.out.println("=== History Conventions Feature ===");
        // m1: AO -> UI : access displayHistoryOfConventions()
        // This method is the entry point
        PointOfRestDTO selected = getSelectedPointOfRest();
        try {
            List<ConventionHistoryDTO> history = historyController.getConventionHistoryForPointOfRest(selected);
            if (history == null) {
                showError("Selected point of rest is not designated");
            } else if (history.isEmpty()) {
                showError("No data available");
            } else {
                showConventionHistory(history);
                // m25: UI -> AO : return display history of conventions
                // Implicitly done by successful display
            }
        } catch (ConnectionException e) {
            showError("Server connection interrupted");
            // m27: UI -> AO : return display error message
            // Implicitly done by showError
        }
    }

    /**
     * Simulates user selecting a point of rest and creates a DTO.
     */
    public PointOfRestDTO getSelectedPointOfRest() {
        // m3: UI -> AO : request point of rest selection
        System.out.println("Please select a point of rest.");
        System.out.print("Enter point of rest ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter point of rest name: ");
        String name = scanner.nextLine();
        System.out.print("Enter point of rest location: ");
        String location = scanner.nextLine();
        // m4: AO -> UI : select point of rest
        // The selection is captured via scanner input
        // m6: UI -> UI : create PointOfRestDTO from selection
        return new PointOfRestDTO(id, name, location);
    }

    /**
     * Displays the convention history list to the user.
     */
    public void showConventionHistory(List<ConventionHistoryDTO> conventionHistoryDTOs) {
        System.out.println("--- Convention History ---");
        for (ConventionHistoryDTO dto : conventionHistoryDTOs) {
            System.out.println("Convention ID: " + dto.getConventionId());
            System.out.println("Date: " + dto.getConventionDate());
            System.out.println("Details: " + dto.getDetails());
            System.out.println();
        }
    }

    /**
     * Displays an error message to the user.
     */
    public void showError(String message) {
        System.err.println("Error: " + message);
    }

    // Main method to simulate the actor interaction
    public static void main(String[] args) {
        // Setup the components (simplified wiring)
        com.example.cache.CacheManager cacheManager = new com.example.cache.CacheManager();
        com.example.network.ServerConnection connection = new com.example.network.ServerConnection("http://etour.example.com");
        com.example.repository.ETOURConventionHistoryRepository repo = new com.example.repository.ETOURConventionHistoryRepository(connection);
        com.example.service.ConventionHistoryServiceImpl service = new com.example.service.ConventionHistoryServiceImpl(repo, cacheManager);
        HistoryController controller = new HistoryController(service);
        AgencyOperatorUI ui = new AgencyOperatorUI(controller);

        // Simulate actor navigating to the feature
        ui.displayHistoryOfConventions();
    }
}
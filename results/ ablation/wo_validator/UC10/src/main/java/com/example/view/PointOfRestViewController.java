package com.example.view;

import com.example.dto.PointOfRestDetailsDTO;
import com.example.controller.ViewRefreshmentPointUseCaseController;
import com.example.data.DataAccessException;

import java.util.List;

/**
 * Boundary class representing the UI for viewing refreshment points.
 * Communicates with controller and displays results to the user.
 */
public class PointOfRestViewController {
    private ViewRefreshmentPointUseCaseController controller;

    public PointOfRestViewController(ViewRefreshmentPointUseCaseController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of points (called from Search Refreshment Point use case).
     * @param points list of point summaries
     */
    public void displayList(List<PointOfRestSummaryDTO> points) {
        // Simulate displaying list in UI
        System.out.println("Displaying list of points:");
        for (PointOfRestSummaryDTO p : points) {
            System.out.println("- " + p.getName() + " (ID: " + p.getId() + ")");
        }
    }

    /**
     * Called when user selects a point from the list.
     * Initiates the sequence to fetch and display details.
     * @param pointId the selected point ID
     */
    public void onPointSelected(String pointId) {
        try {
            // Call controller to get details
            PointOfRestDetailsDTO dto = controller.getPointOfRestDetails(pointId);
            // Prepare UI data (could involve additional formatting)
            prepareUIData(dto);
            // Display details to user
            displayDetails(dto);
        } catch (DataAccessException e) {
            // Display error if data retrieval fails
            displayError("Cannot load details: " + e.getMessage());
        }
    }

    /**
     * Displays detailed information about a point of rest.
     * @param dto the details to display
     */
    public void displayDetails(PointOfRestDetailsDTO dto) {
        System.out.println("\n--- Point of Rest Details ---");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Address: " + dto.getFullAddress());
        System.out.println("Facilities: " + String.join(", ", dto.getFacilities()));
        System.out.println("----------------------------\n");
    }

    /**
     * Displays an error message to the user.
     * @param message error message
     */
    public void displayError(String message) {
        System.err.println("ERROR: " + message);
    }

    /**
     * Prepares UI data (e.g., formatting, localization).
     * Placeholder for actual UI preparation logic.
     */
    private void prepareUIData(PointOfRestDetailsDTO dto) {
        // Could manipulate DTO for UI presentation
        // For simplicity, we do nothing extra here
    }

    /**
     * DTO for point summary used in displayList (assumed from context).
     */
    public static class PointOfRestSummaryDTO {
        private String id;
        private String name;

        public PointOfRestSummaryDTO(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() { return id; }
        public String getName() { return name; }
    }
}
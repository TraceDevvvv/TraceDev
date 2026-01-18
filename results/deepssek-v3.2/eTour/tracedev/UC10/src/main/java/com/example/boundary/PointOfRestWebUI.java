package com.example.boundary;

import com.example.controller.ViewPointOfRestDetailsUseCaseController;
import com.example.dto.PointOfRestDto;
import com.example.service.AuthenticationService;
import com.example.infrastructure.DataAccessException;
import java.util.List;

/**
 * Boundary class representing the web UI for point of rest operations.
 */
public class PointOfRestWebUI {
    private AuthenticationService authService;
    private ViewPointOfRestDetailsUseCaseController controller;

    public PointOfRestWebUI(AuthenticationService authService, ViewPointOfRestDetailsUseCaseController controller) {
        this.authService = authService;
        this.controller = controller;
    }

    /**
     * Displays search results (from previous use case).
     * REQ: Flow of Events: 1
     * @param list list of point of rest summaries.
     */
    public void displaySearchResults(List<Object> list) {
        System.out.println("Displaying search results: " + list.size() + " items found.");
    }

    /**
     * Called when the user selects a point of rest.
     * REQ: Flow of Events: 2
     * @param id the selected point of rest ID.
     */
    public void selectPointOfRest(int id) {
        System.out.println("Point of rest selected with ID: " + id);
    }

    /**
     * Activates the view details function.
     * REQ: Flow of Events: 3
     */
    public void activateViewDetails() {
        System.out.println("View details function activated.");
    }

    /**
     * Renders the detail view (internal step before displaying).
     */
    public void renderDetailView() {
        System.out.println("Rendering detail view...");
    }

    /**
     * Displays point of rest details to the user.
     * REQ: Exit Conditions
     * @param dto the data transfer object containing details.
     */
    public void displayPointOfRestDetails(PointOfRestDto dto) {
        System.out.println("=== Point of Rest Details ===");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Location: " + dto.getLocation());
        System.out.println("Amenities: " + dto.getAmenities());
        System.out.println("=============================");
    }

    /**
     * Shows an error message to the user.
     * REQ: Data Access Exception flow
     * @param message the error message.
     */
    public void showError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Displays list of points of rest (sequence diagram message m3).
     * @param list the list of points of rest.
     */
    public void displayListOfPointsOfRest(List<Object> list) {
        System.out.println("Displaying list of points of rest: " + list.size() + " items.");
    }

    /**
     * Main method that simulates the sequence diagram flow.
     * This method is called from the main application to run the use case.
     * @param pointOfRestId the ID of the point of rest to view.
     */
    public void runViewDetailsFlow(int pointOfRestId) {
        // Entry Condition: Agency Operator IS logged in
        boolean loggedIn = authService.checkLoginStatus();
        if (!loggedIn) {
            showError("User is not logged in.");
            return;
        }

        // Step 1: System displays list of points of rest (simulated)
        displayListOfPointsOfRest(List.of()); // empty list for simulation

        // Step 2: Agency Operator selects a point of rest.
        selectPointOfRest(pointOfRestId);

        // Step 3: Agency Operator activates view details.
        activateViewDetails();

        // Step 4: System uploads data for the selected point of rest.
        try {
            PointOfRestDto dto = controller.executeUseCase(pointOfRestId);
            renderDetailView();
            displayPointOfRestDetails(dto);
        } catch (DataAccessException e) {
            // Data retrieval failure
            showError("Cannot load details: " + e.getMessage());
            // In a real UI, you would call something like displayErrorMessage()
        }
    }
}

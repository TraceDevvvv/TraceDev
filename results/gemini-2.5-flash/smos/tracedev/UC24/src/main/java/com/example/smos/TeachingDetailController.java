package com.example.smos;

/**
 * Presentation Layer Controller for managing teaching detail requests.
 * Acts as an intermediary between the view and the application service.
 * Assumes authenticated user context (as per class diagram note).
 */
public class TeachingDetailController {
    // Dependencies as defined in the Class Diagram
    private TeachingService teachingService;
    private TeachingDetailView teachingDetailView;

    /**
     * Constructs a TeachingDetailController with its required dependencies.
     * @param teachingService The application service for teaching operations.
     * @param teachingDetailView The view for displaying teaching details and errors.
     */
    public TeachingDetailController(TeachingService teachingService, TeachingDetailView teachingDetailView) {
        this.teachingService = teachingService;
        this.teachingDetailView = teachingDetailView;
    }

    /**
     * Handles the request to retrieve and display teaching details.
     * This method implements the main control flow from the sequence diagram.
     * Assumes authenticated user context.
     *
     * @param teachingId The ID of the teaching whose details are requested.
     */
    public void requestTeachingDetails(String teachingId) {
        System.out.println("\nController: Received request for teaching ID: " + teachingId);
        // Simulate authenticated user context check (as per UML note)
        boolean isAuthenticated = true; // Assumption: user is authenticated
        if (!isAuthenticated) {
            teachingDetailView.displayErrorMessage("User not authenticated.");
            return;
        }

        try {
            // Call to TeachingService as per sequence diagram
            TeachingDto teachingDto = teachingService.getTeachingDetails(teachingId);

            if (teachingDto != null) {
                // Call to TeachingDetailView for success path
                teachingDetailView.displayTeachingDetails(teachingDto);
            } else {
                // Handle case where teaching was not found (not a connection error)
                teachingDetailView.displayErrorMessage("Teaching with ID '" + teachingId + "' not found.");
            }
        } catch (ConnectionException e) {
            // Handle ConnectionException as per sequence diagram alternative path
            System.err.println("Controller: Caught ConnectionException: " + e.getMessage());
            teachingDetailView.displayErrorMessage("SMOS server connection interrupted: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.err.println("Controller: An unexpected error occurred: " + e.getMessage());
            teachingDetailView.displayErrorMessage("An unexpected error occurred: " + e.getMessage());
        }
    }
}
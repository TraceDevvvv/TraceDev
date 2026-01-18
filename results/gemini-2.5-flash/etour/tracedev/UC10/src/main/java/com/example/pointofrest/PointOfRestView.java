package com.example.pointofrest;

/**
 * Represents the User Interface (UI) component for displaying PointOfRest details.
 * It's responsible for presenting information to the Agency Operator and capturing user actions.
 */
public class PointOfRestView {

    private PointOfRestController controller; // Reference to the controller to notify of UI events
    private String currentlySelectedPointOfRestId; // Stores the ID of the currently selected Point of Rest

    public PointOfRestView() {
        // Default constructor
    }

    /**
     * Sets the controller for this view. This is typically done during application setup.
     * @param controller The PointOfRestController instance.
     */
    public void setController(PointOfRestController controller) {
        this.controller = controller;
    }

    /**
     * Displays the detailed information of a PointOfRest.
     *
     * @param dto The PointOfRestDetailsDto containing the details to display.
     */
    public void displayPointOfRestDetails(PointOfRestDetailsDto dto) {
        System.out.println("\n--- Point Of Rest Details ---");
        if (dto != null) {
            System.out.println("ID: " + dto.id);
            System.out.println("Name: " + dto.name);
            System.out.println("Address: " + dto.address);
            System.out.println("Type: " + dto.type);
            System.out.println("Description: " + dto.description);
            System.out.println("Contact Info: " + dto.contactInfo);
        } else {
            System.out.println("No details available.");
        }
        System.out.println("---------------------------\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n--- ERROR ---");
        System.err.println("Message: " + message);
        System.err.println("-------------\n");
    }

    /**
     * Simulates an Agency Operator selecting a point of rest from a list.
     * This method stores the selected ID for subsequent actions.
     * Corresponds to message 'selectPointOfRest(pointOfRestId : String)' in sequence diagram (m2).
     *
     * @param pointOfRestId The ID of the selected point of rest.
     */
    public void selectPointOfRest(String pointOfRestId) {
        System.out.println("[PointOfRestView] Agency Operator selected PointOfRest with ID: " + pointOfRestId);
        this.currentlySelectedPointOfRestId = pointOfRestId;
    }

    /**
     * Simulates an Agency Operator activating the function to view details of the selected point of rest.
     * This method notifies the controller to handle the actual logic, using the previously selected ID.
     * Corresponds to message 'activateViewDetails()' in sequence diagram (m4).
     */
    public void activateViewDetails() {
        if (currentlySelectedPointOfRestId == null || currentlySelectedPointOfRestId.isEmpty()) {
            System.err.println("[PointOfRestView] Error: No Point of Rest selected to view details.");
            displayErrorMessage("Please select a Point of Rest before activating 'View Details'.");
            return;
        }

        System.out.println("[PointOfRestView] Agency Operator activated 'View Details' for ID: " + currentlySelectedPointOfRestId);
        if (controller != null) {
            controller.viewPointOfRestDetails(currentlySelectedPointOfRestId);
        } else {
            System.err.println("[PointOfRestView] Error: Controller not set for the view.");
        }
    }
}
package com.example.tourist;

import java.util.Scanner;

/**
 * Represents the User Interface (View) for displaying and editing Tourist data.
 * It interacts with the AgencyOperatorController.
 */
public class TouristFormView {
    private AgencyOperatorController controller;
    private final Scanner scanner = new Scanner(System.in); // For simulating user input

    public TouristFormView() {
        // Default constructor
    }

    /**
     * Sets the controller for this view. This is typically done during initialization.
     * @param controller The controller instance to interact with.
     */
    public void setController(AgencyOperatorController controller) {
        this.controller = controller;
    }

    /**
     * Displays the tourist data on the form (simulated via console output).
     * Corresponds to `Controller -> View: displayTouristData(touristDTO)`
     *
     * @param touristDTO The DTO containing tourist data to display.
     */
    public void displayTouristData(TouristDTO touristDTO) {
        System.out.println("\n--- Displaying Tourist Data ---");
        if (touristDTO != null) {
            System.out.println("ID:        " + touristDTO.id);
            System.out.println("Name:      " + touristDTO.name);
            System.out.println("Surname:   " + touristDTO.surname);
            System.out.println("Email:     " + touristDTO.email);
            System.out.println("Address:   " + touristDTO.address);
            System.out.println("-----------------------------");
        } else {
            System.out.println("No tourist data to display.");
        }
    }

    /**
     * Simulates getting edited tourist data from the form fields.
     * In a real application, this would read from UI components.
     * Corresponds to `View -> View: getEditedTouristData()`
     *
     * For simulation: returns a hardcoded edited DTO for an existing ID.
     * The ID should typically come from the displayed tourist.
     *
     * @return A TouristDTO with simulated edited data.
     */
    public TouristDTO getEditedTouristData() {
        System.out.println("\n[TouristFormView] Simulating user editing the form...");
        // This is a mock. In a real UI, it would gather values from input fields.
        // Let's assume we are editing T001
        TouristDTO editedDTO = new TouristDTO(
            "T001",                 // ID (should remain constant for update)
            "Alice Updated",        // Changed name
            "Smith-Jackson",        // Changed surname
            "alice.u.smith@example.com", // Changed email
            "456 New Road, Updated City" // Changed address
        );
        System.out.println("Simulated edited data: " + editedDTO);
        return editedDTO;
    }

    /**
     * Displays a confirmation prompt to the user (simulated via console).
     * Corresponds to `Controller -> View: showConfirmationPrompt(message)`
     * If user confirms, it calls `controller.confirmUpdateOperation()`.
     *
     * @param message The confirmation message.
     */
    public void showConfirmationPrompt(String message) {
        System.out.println("\n[TouristFormView] " + message);
        // In a real UI, this would show a dialog with 'Yes'/'No' buttons.
        // For simulation, we expect the main method to trigger the confirmation
        // after this message is displayed, based on a separate user input.
        System.out.println("Awaiting confirmation from user (handled by main method).");
    }

    /**
     * Displays a success message to the user (simulated via console).
     * Corresponds to `Controller -> View: showSuccessMessage(message)`
     * @param message The success message.
     */
    public void showSuccessMessage(String message) {
        System.out.println("\n[TouristFormView] SUCCESS: " + message);
    }

    /**
     * Displays an error message to the user (simulated via console).
     * Corresponds to `Controller -> View: showErrorMessage(message)`
     * @param message The error message.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n[TouristFormView] ERROR: " + message);
    }

    /**
     * Displays a login prompt (simulated via console).
     * Corresponds to `AO -> View: showLoginPrompt()`
     */
    public void showLoginPrompt() {
        System.out.println("\n[TouristFormView] Please log in to perform this action.");
        // In a real application, this would redirect to a login screen.
    }
}
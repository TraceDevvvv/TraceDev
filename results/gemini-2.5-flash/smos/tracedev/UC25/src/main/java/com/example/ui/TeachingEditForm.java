package com.example.ui;

import com.example.controller.TeachingController;
import com.example.exceptions.ServiceException;
import com.example.model.TeachingDTO;
import com.example.model.ValidationResult;
import com.example.service.TeachingDetailsService;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the UI form for editing teaching details.
 * Interacts with the TeachingController and displays messages to the user.
 */
public class TeachingEditForm {
    private final TeachingController teachingController;
    private final TeachingDetailsService teachingDetailsService; // Used for initial display (precondition)
    private TeachingDTO currentEditedTeaching; // Simulates the data currently in form fields

    /**
     * Constructs a TeachingEditForm.
     * @param controller The controller responsible for handling form actions.
     * @param detailsService The service to fetch initial teaching details (precondition).
     */
    public TeachingEditForm(TeachingController controller, TeachingDetailsService detailsService) {
        this.teachingController = controller;
        this.teachingDetailsService = detailsService;
        // Simulate initial display of teaching details (precondition)
        String initialTeachingId = "T001"; // Assuming a specific teaching is being edited
        this.currentEditedTeaching = teachingDetailsService.getTeachingDetails(initialTeachingId);
        System.out.println("FORM: Precondition fulfilled: Teaching details loaded for editing: " + this.currentEditedTeaching);
    }

    /**
     * Simulates collecting edited data from form fields.
     * In a real UI, this would read from input components.
     * For this example, it returns a modified version of the initially loaded teaching.
     * @return A TeachingDTO representing the data currently in the form fields.
     */
    public TeachingDTO getEditedTeachingData() {
        // Simulate changes made by the user in the form
        // For demonstration, let's change the description and name of the current edited teaching
        // to simulate user input.
        // We'll return a *new* DTO to reflect potential user changes, keeping original currentEditedTeaching.
        // This is a simple simulation, typically form fields would be bound to DTO properties directly.
        System.out.println("FORM: Collecting edited data from form fields.");
        return new TeachingDTO(
            currentEditedTeaching.id,
            currentEditedTeaching.name + " (edited)",
            currentEditedTeaching.description + " (updated by Admin)"
        );
    }

    /**
     * Displays an error message to the administrator.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("FORM ERROR: " + message);
    }

    /**
     * Displays multiple error messages to the administrator.
     * @param messages A list of error messages to display.
     */
    public void displayErrorMessage(List<String> messages) {
        System.err.println("FORM ERRORS:");
        messages.forEach(msg -> System.err.println(" - " + msg));
    }

    /**
     * Displays a success message to the administrator.
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("FORM SUCCESS: " + message);
    }

    /**
     * Displays an updated list of teachings.
     * @param teachings The list of TeachingDTOs to display.
     */
    public void displayUpdatedTeachingsList(List<TeachingDTO> teachings) {
        System.out.println("\nFORM: Displaying updated teachings list:");
        if (teachings.isEmpty()) {
            System.out.println("  (No teachings available)");
        } else {
            teachings.forEach(t -> System.out.println("  - " + t));
        }
        System.out.println("-------------------------------------");
    }

    /**
     * Handles the save button click event from the UI.
     * Initiates the process of saving edited teaching details.
     */
    public void onSaveButtonClick() {
        System.out.println("\nFORM: 'Save' button clicked. Initiating save process.");
        try {
            // 1. Collect data from form fields
            TeachingDTO editedData = getEditedTeachingData();

            // 2. Delegate to the controller to save the teaching
            ValidationResult result = teachingController.saveTeaching(editedData);

            if (result.hasErrors()) {
                displayErrorMessage(result.getErrors());
            } else {
                displaySuccessMessage("Teaching updated successfully.");
                // After successful update, refresh the list displayed in the UI
                List<TeachingDTO> updatedTeachings = teachingController.refreshTeachingsList();
                displayUpdatedTeachingsList(updatedTeachings);
                // Update the form's internal representation of the teaching
                this.currentEditedTeaching = editedData; // Or fetch again from service if needed
            }
        } catch (ServiceException e) {
            displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the cancel button click event from the UI.
     * Notifies the controller and provides feedback to the user.
     */
    public void onCancelButtonClick() {
        System.out.println("\nFORM: 'Cancel' button clicked. Initiating cancel process.");
        teachingController.cancelEditTeaching();
        displaySuccessMessage("Operation cancelled.");
        // In a real UI, this might clear form fields or navigate back.
        // For this simulation, we just confirm cancellation.
    }

    /**
     * Simulates the interaction flow.
     */
    public void simulateUserInteraction(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Teaching Edit Form ---");
            System.out.println("Current Teaching: " + currentEditedTeaching);
            System.out.println("1. Save Changes");
            System.out.println("2. Cancel Edit");
            System.out.println("3. Exit Simulation");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    onSaveButtonClick();
                    break;
                case "2":
                    onCancelButtonClick();
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting simulation.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
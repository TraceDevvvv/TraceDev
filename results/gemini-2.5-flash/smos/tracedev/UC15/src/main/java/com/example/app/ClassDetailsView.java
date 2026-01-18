package com.example.app;

import java.util.Scanner; // For simulating user input in getClickedClassId

/**
 * Mock UI component for displaying class details and handling user interactions.
 * This class corresponds to the 'ClassDetailsView' class in the UML Class Diagram.
 * It simulates the view layer for the "Administrator Views Class Details" use case.
 */
public class ClassDetailsView {
    private ClassDetailsController controller; // Dependency on Controller

    /**
     * Sets the controller for this view.
     * This is a dependency injection method, allowing the controller to be set after view instantiation.
     * @param controller The ClassDetailsController instance.
     */
    public void setController(ClassDetailsController controller) {
        this.controller = controller;
    }

    /**
     * Displays the class details to the user.
     *
     * @param details The ClassDetailsDTO containing the information to display.
     */
    public void displayClassDetails(ClassDetailsDTO details) {
        System.out.println("\n--- Class Details View ---");
        if (details != null) {
            System.out.println("Name: " + details.getName());
            System.out.println("Address: " + details.getAddress());
            System.out.println("School Year: " + details.getSchoolYear());
            System.out.println("--------------------------");
            System.out.println("View: display(classDetailsDTO)"); // Sequence Diagram note
        } else {
            System.out.println("No class details available.");
            System.out.println("--------------------------");
        }
    }

    /**
     * Simulates getting the ID of a class clicked by the user.
     * For demonstration, this can return a hardcoded value or prompt for input.
     *
     * @return The ID of the clicked class.
     */
    public String getClickedClassId() {
        // In a real GUI, this would be triggered by a click event and return the ID.
        // For simulation, let's assume a default ID or prompt the user.
        System.out.println("\nView: Simulating user selection. Enter class ID (e.g., CLASS001): ");
        try (Scanner scanner = new Scanner(System.in)) {
             String input = scanner.nextLine();
             return input.isEmpty() ? "CLASS001" : input; // Default to CLASS001 if empty
        } catch (Exception e) {
            System.err.println("Error reading input: " + e.getMessage());
            return "CLASS001"; // Fallback
        }
    }

    /**
     * Handles the event when the "Show details" button is clicked for a specific class.
     * This method is the entry point for the "Administrator Views Class Details" sequence diagram (R6).
     *
     * @param classId The ID of the class whose details are to be shown.
     */
    public void handleShowDetailsClick(String classId) {
        System.out.println("\nView: handleShowDetailsClick(" + classId + ") - Administrator selects a class and clicks \"Show details\".");
        // Sequence Diagram: View -> Controller: showClassDetails(classId)
        if (controller != null) {
            try {
                controller.showClassDetails(classId);
            } catch (ConnectionException e) {
                // This catch block is for exceptions propagated up, which should be handled by displayErrorMessage.
                // The sequence diagram shows controller calling displayErrorMessage, so this might be redundant in a typical flow.
                displayErrorMessage("An unexpected connection error occurred: " + e.getMessage());
            }
        } else {
            System.err.println("View Error: Controller not set for ClassDetailsView.");
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!");
        System.out.println("View --> Admin: displayErrorMessage(\"" + message + "\")"); // Sequence Diagram note
    }
}
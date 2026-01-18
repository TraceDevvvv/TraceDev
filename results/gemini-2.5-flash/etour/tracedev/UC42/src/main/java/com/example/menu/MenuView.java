package com.example.menu;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Presentation layer component responsible for displaying menu-related information
 * and capturing user input. It interacts with the MenuController.
 * This is a console-based view for demonstration purposes.
 */
public class MenuView {
    private MenuController controller;
    private Scanner scanner;
    private DayOfWeek selectedDay; // Stores the currently selected day within the view

    /**
     * Constructs a new MenuView.
     *
     * @param controller The MenuController that this view will interact with.
     */
    public MenuView(MenuController controller) {
        this.controller = Objects.requireNonNull(controller, "MenuController cannot be null");
        this.scanner = new Scanner(System.in);
        // The controller is set after the view's constructor as part of wiring.
        // This circular dependency is common in MVC/MVP and resolved during setup.
    }

    // Setter for controller to resolve circular dependency if needed, though constructor can often handle it.
    public void setController(MenuController controller) {
        this.controller = controller;
    }

    /**
     * Displays a form to delete a daily menu, listing available days.
     * REQ-R5: Displays a list of DayOfWeek values provided by the controller.
     *
     * @param days A list of DayOfWeek objects to display.
     */
    public void displayDeleteMenuForm(List<DayOfWeek> days) {
        System.out.println("\n--- Delete Daily Menu ---");
        System.out.println("Available days with menus:");
        if (days.isEmpty()) {
            System.out.println("No days available to display. (This is a mock list)");
            return;
        }
        for (int i = 0; i < days.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), days.get(i));
        }
        System.out.println("Please select a day to delete (enter number):");

        // Simulate user selection
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= days.size()) {
                onDaySelected(days.get(choice - 1)); // REQ-R6
            } else {
                displayErrorMessage("Invalid selection. Please try again.");
                displayDeleteMenuForm(days); // Re-display form
            }
        } catch (NumberFormatException e) {
            displayErrorMessage("Invalid input. Please enter a number.");
            displayDeleteMenuForm(days); // Re-display form
        }
    }

    /**
     * REQ-R6, REQ-R7, REQ-R9, REQ-R12: Handles the selection of a day by the user.
     * Stores the selected day and informs the controller.
     *
     * @param day The DayOfWeek that was selected.
     */
    public void onDaySelected(DayOfWeek day) {
        this.selectedDay = day;
        System.out.println("[MenuView] Day selected: " + selectedDay);
        // The sequence diagram implies the view immediately submits after selection
        // In a real UI, there would be a separate "Submit" button.
        // For console, let's auto-submit the delete request for the selected day.
        // Or, better, let's explicitly simulate the 'submitDeleteRequest' call after selection.
        controller.handleDaySelection(selectedDay); // Inform controller of selection
        System.out.println("Press Enter to submit deletion request for " + selectedDay + " or type 'cancel' to abort.");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("cancel")) {
            onCancelOperation();
        } else {
            onSubmitDeleteRequest(); // REQ-R6, REQ-R7, REQ-R9, REQ-R12
        }
    }

    /**
     * Displays a confirmation prompt before proceeding with deletion.
     *
     * @param dayOfWeek The day for which deletion is being confirmed.
     */
    public void displayConfirmationPrompt(DayOfWeek dayOfWeek) {
        System.out.printf("\nAre you sure you want to delete the menu for %s? (yes/no)\n", dayOfWeek);
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            onConfirmDeletion(); // REQ-R6, REQ-R7, REQ-R9, REQ-R12
        } else {
            onCancelOperation(); // REQ-R6, REQ-R7, REQ-R9, REQ-R12
        }
    }

    /**
     * REQ-R6, REQ-R7, REQ-R9, REQ-R12: Callback for when the user submits a delete request.
     * Delegates to the controller.
     */
    public void onSubmitDeleteRequest() {
        if (selectedDay != null) {
            System.out.println("[MenuView] Submitting delete request for " + selectedDay);
            controller.handleSubmitDeleteRequest(selectedDay);
        } else {
            displayErrorMessage("No day was selected for deletion.");
        }
    }

    /**
     * REQ-R6, REQ-R7, REQ-R9, REQ-R12: Callback for when the user confirms the deletion.
     * Delegates to the controller.
     */
    public void onConfirmDeletion() {
        if (selectedDay != null) {
            System.out.println("[MenuView] Confirming deletion for " + selectedDay);
            controller.handleConfirmDeletion(selectedDay);
        } else {
            displayErrorMessage("No day was selected for confirmation.");
        }
    }

    /**
     * REQ-R6, REQ-R7, REQ-R9, REQ-R12: Callback for when the user cancels an operation.
     * Delegates to the controller.
     */
    public void onCancelOperation() {
        System.out.println("[MenuView] Operation cancelled.");
        controller.handleCancelOperation();
        this.selectedDay = null; // Clear selected day
    }

    /**
     * Displays a success message to the user.
     *
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("\nSUCCESS: " + message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\nERROR: " + message);
    }

    /**
     * Closes the scanner resource.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
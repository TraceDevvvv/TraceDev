package com.example.smos.presentation;

import com.example.smos.domain.AcademicYear;
import com.example.smos.domain.Class;
import java.util.List;
import java.util.Scanner; // For simulating user input

/**
 * Represents the view component for displaying and interacting with class lists.
 * This class simulates UI interactions using System.out.println and a Scanner.
 */
public class ClassListView {
    private ClassListController controller;
    private Scanner scanner = new Scanner(System.in); // For simulating user input

    /**
     * Sets the controller responsible for handling user actions from this view.
     * @param controller The ClassListController instance.
     */
    public void setController(ClassListController controller) {
        this.controller = controller;
    }

    /**
     * Simulates a user clicking the "Manage Classes" button.
     * This triggers the initial request to the controller.
     */
    public void onManageClassesClicked() {
        System.out.println("\n[View] User clicked 'Manage Classes' button.");
        if (controller != null) {
            controller.handleViewClassListRequest();
        } else {
            System.err.println("[View] Error: Controller not set.");
        }
    }

    /**
     * Displays a selection list of academic years to the user.
     * @param years A list of AcademicYear objects to display.
     */
    public void displayAcademicYearSelection(List<AcademicYear> years) {
        System.out.println("\n--- Academic Year Selection ---");
        if (years == null || years.isEmpty()) {
            System.out.println("No academic years available.");
            return;
        }
        System.out.println("Please select an academic year:");
        for (int i = 0; i < years.size(); i++) {
            System.out.println((i + 1) + ". " + years.get(i).getYear() + " (ID: " + years.get(i).getId() + ")");
        }
        System.out.println("Press 'C' to cancel.");
        System.out.println("-------------------------------");

        // Simulate user input
        System.out.print("Enter selection (number or C to cancel): ");
        String input = scanner.nextLine();

        if ("C".equalsIgnoreCase(input) || "c".equalsIgnoreCase(input)) {
            onCancelButtonClicked();
            return;
        }

        try {
            int selectionIndex = Integer.parseInt(input) - 1;
            if (selectionIndex >= 0 && selectionIndex < years.size()) {
                String selectedYearId = years.get(selectionIndex).getId();
                onAcademicYearSelected(selectedYearId);
            } else {
                System.out.println("[View] Invalid selection. Please try again.");
                displayAcademicYearSelection(years); // Re-prompt
            }
        } catch (NumberFormatException e) {
            System.out.println("[View] Invalid input. Please enter a number or 'C'.");
            displayAcademicYearSelection(years); // Re-prompt
        }
    }

    /**
     * Simulates a user selecting an academic year.
     * This notifies the controller about the selection (R6).
     * @param yearId The ID of the selected academic year.
     */
    public void onAcademicYearSelected(String yearId) {
        System.out.println("[View] User selected Academic Year with ID: " + yearId);
        if (controller != null) {
            controller.selectAcademicYear(yearId);
        }
    }

    /**
     * Displays the class management screen layout (without specific classes yet).
     */
    public void displayClassManagementScreen() {
        System.out.println("\n--- Class Management Screen ---");
        System.out.println("Loading classes...");
    }

    /**
     * Displays a list of classes on the screen.
     * @param classes A list of Class objects to display.
     */
    public void displayClassList(List<Class> classes) {
        System.out.println("\n--- Class List ---");
        if (classes == null || classes.isEmpty()) {
            System.out.println("No classes found for the selected academic year.");
            return;
        }
        for (Class c : classes) {
            System.out.println("- " + c.getName() + " (" + c.getId() + ") - " + c.getDescription());
        }
        System.out.println("------------------");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to show.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n[View] ERROR: " + message);
        System.err.println("Please try again or contact support.");
    }

    /**
     * Handles the event when the cancel button is clicked (R11).
     * It asks for confirmation and then notifies the controller if confirmed.
     */
    public void onCancelButtonClicked() {
        System.out.println("\n[View] User clicked 'Cancel'.");
        if (showCancelConfirmation()) {
            System.out.println("[View] Cancellation confirmed.");
            if (controller != null) {
                controller.cancelClassListOperation();
            }
            showPreviousScreen();
        } else {
            System.out.println("[View] Cancellation not confirmed. Returning to current screen.");
            // In a real UI, this would typically refresh the current view or do nothing.
            // For this simulation, we just print a message.
        }
    }

    /**
     * Simulates navigating back to the previous screen (R11).
     * For this console application, it just prints a message.\n     */
    public void showPreviousScreen() {
        System.out.println("\n[View] Navigating back to previous screen (e.g., Main Menu).");
        // In a real application, this would trigger a navigation event.
    }

    /**
     * Optional: Shows a confirmation dialog for cancellation (R11 recommendation).\n     * For simulation, it always returns true.
     * @return true if the user confirms cancellation, false otherwise.
     */
    public boolean showCancelConfirmation() {
        System.out.print("[View] Are you sure you want to cancel? (y/n): ");
        String input = scanner.nextLine();
        return "y".equalsIgnoreCase(input) || "yes".equalsIgnoreCase(input);
    }
}
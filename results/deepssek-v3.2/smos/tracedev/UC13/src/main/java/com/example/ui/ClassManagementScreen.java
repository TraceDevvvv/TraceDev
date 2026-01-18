package com.example.ui;

import com.example.model.Administrator;
import com.example.model.Class;
import com.example.controller.ViewClassListController;
import com.example.exception.ConnectionException;
import java.util.List;
import java.util.Scanner;

/**
 * Boundary class representing the Class Management screen.
 */
public class ClassManagementScreen {
    private String selectedAcademicYear;
    private Administrator administrator;
    private ViewClassListController controller;
    private Scanner scanner;

    public ClassManagementScreen(Administrator administrator, ViewClassListController controller) {
        this.administrator = administrator;
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Verifies that the user is logged in (REQ-EC1).
     * @return true if logged in, false otherwise
     */
    public boolean verifyLogin() {
        return administrator != null && administrator.isLoggedIn();
    }

    /**
     * Displays the initial screen with academic year selector.
     */
    public void display() {
        System.out.println("=== Class Management Screen ===");
        System.out.println("Please select an academic year:");
        // In a real UI, this would be a dropdown or input field
    }

    /**
     * Shows the academic year selector.
     */
    public void showAcademicYearSelector() {
        display();
    }

    /**
     * Simulates the user selecting the Class Management button.
     * This is the entry point of the use case.
     */
    public void selectClassManagement() {
        System.out.println("Class Management selected.");
        display();
    }

    /**
     * Handles the user selecting an academic year.
     * @param year the selected academic year
     */
    public void selectAcademicYear(String year) {
        this.selectedAcademicYear = year;
        System.out.println("Selected academic year: " + year);

        try {
            // Call controller to execute use case
            List<Class> classes = controller.execute(year);
            showListOfClasses(classes);
        } catch (ConnectionException e) {
            // Handle connection failure (REQ-EC2)
            handleInterruption();
            displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Displays the list of classes to the administrator.
     * @param classes the list of classes to display
     */
    public void showListOfClasses(List<Class> classes) {
        // REQ-Q1: The list of classes should be displayed accurately and promptly
        System.out.println("\n=== List of Classes for Academic Year " + selectedAcademicYear + " ===");
        if (classes.isEmpty()) {
            System.out.println("No classes found.");
        } else {
            for (Class cls : classes) {
                System.out.println(cls);
            }
        }
        System.out.println("=====================================");
        // Exit Condition: System shows the list of classes
    }

    /**
     * Handles interruption scenarios (connection loss or user cancellation).
     */
    public void handleInterruption() {
        System.out.println("Operation interrupted.");
        // Could perform cleanup or reset UI state
    }

    /**
     * Cancels the current operation when the administrator interrupts (REQ-EC3).
     */
    public void cancelOperation() {
        System.out.println("Operation cancelled by administrator.");
        handleInterruption();
    }

    /**
     * Displays an error message to the administrator.
     * @param errorMessage the error message to display
     */
    public void displayErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Shows an empty list or error message.
     * @param message the message to display
     */
    public void showEmptyListOrErrorMessage(String message) {
        System.out.println("No data available: " + message);
    }

    public String getSelectedAcademicYear() {
        return selectedAcademicYear;
    }

    public void setSelectedAcademicYear(String selectedAcademicYear) {
        this.selectedAcademicYear = selectedAcademicYear;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ViewClassListController getController() {
        return controller;
    }

    public void setController(ViewClassListController controller) {
        this.controller = controller;
    }
}
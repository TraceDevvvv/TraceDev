package com.example;

import java.util.List;
import java.util.Scanner;

// Presentation component for displaying and collecting class creation data.
public class ClassForm {
    private Scanner scanner;
    private AdministratorController controller; // Reference to controller for callbacks

    /**
     * Constructor for ClassForm.
     * @param controller The AdministratorController to which events will be reported.
     */
    public ClassForm(AdministratorController controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
        System.out.println("ClassForm initialized.");
    }

    /**
     * Displays the class creation form to the administrator (step 2).
     */
    public void displayForm() {
        System.out.println("\n--- New Class Creation Form ---");
        System.out.println("Please enter class details or type 'cancel' to abort.");
        // Admin <- Form : Form displayed (simulated by console output)
    }

    /**
     * Simulates getting form data from user input and creates a ClassCreationDTO.
     * This method will also simulate the user choosing to cancel or save.
     *
     * @return A ClassCreationDTO if user provides input and doesn't cancel, null if cancelled.
     */
    public ClassCreationDTO getFormData() {
        System.out.print("Class Name: ");
        String name = scanner.nextLine();
        if ("cancel".equalsIgnoreCase(name.trim())) {
            cancelOperation();
            return null;
        }

        System.out.print("Class Address: ");
        String address = scanner.nextLine();
        if ("cancel".equalsIgnoreCase(address.trim())) {
            cancelOperation();
            return null;
        }

        System.out.print("Academic Year (e.g., 2023-2024): ");
        String academicYear = scanner.nextLine();
        if ("cancel".equalsIgnoreCase(academicYear.trim())) {
            cancelOperation();
            return null;
        }

        System.out.println("Form: User filled out form and clicked Save (simulated).");
        // Form -> DTO : <<create>> new ClassCreationDTO(name, address, academicYear)
        return new ClassCreationDTO(name, address, academicYear);
    }

    /**
     * Displays error messages to the user.
     *
     * @param errors A list of error strings.
     */
    public void showErrorMessage(List<String> errors) {
        System.err.println("\n--- Form Submission Error ---");
        System.err.println("The following issues were found:");
        errors.forEach(System.err::println);
        System.err.println("Please correct them and try again.");
        // Form --> Controller : error message shown
        System.out.println("Administrator has been notified of a data error (Exit Condition).");
    }

    /**
     * Displays a success message to the user.
     */
    public void showSuccessMessage() {
        System.out.println("\n--- Success ---");
        System.out.println("New class has been successfully entered (Exit Condition).");
        // Form --> Controller : success message shown
    }

    /**
     * Handles the cancellation of the operation by the user (R16).
     * This method is called internally by ClassForm if the user types 'cancel'.
     */
    public void cancelOperation() {
        System.out.println("ClassForm: User initiated cancellation.");
        // Form -> Controller : handleCancellation()
        controller.handleCancellation();
    }
}

package com.example.justice.view;

import com.example.justice.controller.AdminController;
import com.example.justice.domain.Justice;
import com.example.justice.dto.JusticeUpdateDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Simulates the Justice Update Form View (Presentation Layer).
 * This class handles displaying justice details, getting user input,
 * and showing messages to the administrator.
 */
public class JusticeUpdateFormView {
    private AdminController adminController; // Dependency on the controller
    private final Scanner scanner; // For simulating user input

    /**
     * Constructs a JusticeUpdateFormView.
     *
     * @param adminController The controller responsible for handling user actions.
     */
    public JusticeUpdateFormView(AdminController adminController) {
        this.adminController = adminController;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the details of a Justice record to the user.
     * This method is called as part of the "Entry Conditions" flow to show existing data.
     *
     * @param justice The Justice object to display.
     */
    public void displayJusticeDetails(Justice justice) {
        if (justice == null) {
            System.out.println("No justice details to display. Justice not found.");
            return;
        }
        System.out.println("\n--- Current Justice Details ---");
        System.out.println("ID: " + justice.getId());
        System.out.println("Date Justification: " + (justice.getDateJustification() != null ? new SimpleDateFormat("yyyy-MM-dd").format(justice.getDateJustification()) : "N/A"));
        System.out.println("Status: " + justice.getStatus());
        System.out.println("-----------------------------\n");
    }

    /**
     * Simulates getting user input from the form.
     * Creates and returns a JusticeUpdateDTO with the entered data.
     * For this simulation, it will hardcode values or take simple console input.
     *
     * @param existingJusticeId The ID of the justice being updated, to be included in the DTO.
     * @return A JusticeUpdateDTO containing the simulated form input.
     */
    public JusticeUpdateDTO getFormInput(String existingJusticeId) {
        System.out.println("Enter new date of justification (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        Date newDate = null;
        try {
            newDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.err.println("Invalid date format. Using null for date justification. Error: " + e.getMessage());
            // In a real UI, this would show an error message.
        }
        // The JusticeUpdateDTO constructor requires String id, Date dateJustification, String status.
        // As the form input here only collects date, and the DTO constructor requires a status,
        // we provide null for the status, indicating it's not being updated via this specific input.
        return new JusticeUpdateDTO(existingJusticeId, newDate, null);
    }

    /**
     * Displays a success message to the user.
     *
     * @param message The success message to display.
     */
    public void showSuccessMessage(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("[ERROR] " + message);
    }

    /**
     * Simulates navigation back to the registry screen after an operation.
     */
    public void navigateToRegistryScreen() {
        System.out.println("Navigating back to the Justice Registry screen...");
        // In a real application, this would involve routing to another UI component.
    }

    /**
     * Triggers the update process from the view, simulating a "Save" button click.
     * This method connects the view to the controller as per the sequence diagram.
     *
     * @param justiceId The ID of the justice to be updated.
     */
    public void triggerUpdate(String justiceId) {
        System.out.println("\n--- Simulating Admin changing fields and clicking 'Save' ---");
        JusticeUpdateDTO justiceDto = getFormInput(justiceId);
        // The view passes the DTO to the controller
        adminController.updateJustice(justiceDto);
    }
}

package com.example.presentation;

import com.example.application.controller.ModifyTouristUseCaseController;
import com.example.application.dto.ModifyTouristDTO;
import java.util.Scanner;

/**
 * Presentation layer form for interacting with the Agency Operator.
 * Simulates a UI form with console input/output.
 */
public class TouristForm {
    private ModifyTouristUseCaseController controller;
    private Long currentTouristId;
    private Scanner scanner;

    public TouristForm(ModifyTouristUseCaseController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a list of tourists (simulated).
     */
    public void displayTouristList() {
        System.out.println("=== Tourist List ===");
        System.out.println("1. John Doe (ID: 101)");
        System.out.println("2. Jane Smith (ID: 102)");
        System.out.println("3. Bob Johnson (ID: 103)");
    }

    /**
     * Stores the selected tourist ID.
     */
    public void selectTourist(Long touristId) {
        this.currentTouristId = touristId;
        System.out.println("Selected tourist ID: " + touristId);
    }

    /**
     * Loads tourist data for the stored touristId and displays it.
     */
    public ModifyTouristDTO loadTouristData() {
        if (currentTouristId == null) {
            System.out.println("No tourist selected.");
            return null;
        }
        ModifyTouristDTO dto = controller.loadTouristData(currentTouristId);
        displayTouristData(dto);
        return dto;
    }

    /**
     * Displays the tourist data from the DTO.
     */
    public void displayTouristData(ModifyTouristDTO dto) {
        System.out.println("=== Tourist Details ===");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Email: " + dto.getEmail());
        System.out.println("Phone: " + dto.getPhoneNumber());
        System.out.println("Nationality: " + dto.getNationality());
    }

    /**
     * Collects user input for modifying tourist data.
     * Simulates form input via console.
     */
    public ModifyTouristDTO collectUserInput() {
        System.out.println("=== Edit Tourist Details ===");
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter new nationality: ");
        String nationality = scanner.nextLine();

        return new ModifyTouristDTO(currentTouristId, name, email, phone, nationality);
    }

    /**
     * Submits the form with the modified DTO.
     * Handles validation, confirmation, and update.
     */
    public boolean submitForm(ModifyTouristDTO dto) {
        // Delegate to controller for modification
        boolean success = controller.modifyTouristData(dto);
        if (!success) {
            showErrorMessage();
            return false;
        }

        // Request confirmation from user
        if (!showConfirmationDialog()) {
            System.out.println("Operation cancelled by user.");
            return false;
        }

        // Proceed with update after confirmation
        success = controller.proceedWithUpdate(dto);
        if (success) {
            showSuccessMessage();
        } else {
            showErrorMessage();
        }
        return success;
    }

    /**
     * Shows a confirmation dialog and returns user's choice.
     */
    public boolean showConfirmationDialog() {
        System.out.print("Confirm update? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    /**
     * Shows a success message.
     */
    public void showSuccessMessage() {
        System.out.println("Tourist data updated successfully!");
    }

    /**
     * Shows an error message.
     */
    public void showErrorMessage() {
        System.out.println("Error: Failed to update tourist data. Please check the input and try again.");
    }

    /**
     * Simulates the main flow as per the sequence diagram.
     */
    public void runModifyFlow() {
        displayTouristList();
        // Simulate selecting tourist with ID 101
        selectTourist(101L);
        ModifyTouristDTO loadedDto = loadTouristData();
        if (loadedDto == null) return;

        ModifyTouristDTO modifiedDto = collectUserInput();
        boolean submitted = submitForm(modifiedDto);
        if (submitted) {
            System.out.println("Modify tourist account flow completed.");
        } else {
            System.out.println("Modify tourist account flow failed.");
        }
    }
}
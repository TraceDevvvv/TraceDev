package com.example.boundary;

import com.example.controller.RefreshmentPointController;
import com.example.dto.ModifyRefreshmentPointRequestDto;
import com.example.dto.ResultDto;
import java.util.Scanner;

/**
 * Boundary class representing the user interface form.
 * For demonstration, this uses console I/O.
 */
public class RefreshmentPointForm {
    
    private String pointId;
    private ModifyRefreshmentPointRequestDto requestDto;
    private final RefreshmentPointController refreshmentPointController;
    private final Scanner scanner = new Scanner(System.in);

    public RefreshmentPointForm(RefreshmentPointController refreshmentPointController) {
        this.refreshmentPointController = refreshmentPointController;
    }

    /**
     * Step 1: Actor selects a refreshment point to modify.
     */
    public void enableModifyFunction(String pointId) {
        this.pointId = pointId;
        System.out.println("Modify function enabled for point ID: " + pointId);
        loadRefreshmentPointData(pointId);
    }

    public void loadRefreshmentPointData(String id) {
        ModifyRefreshmentPointRequestDto dto = refreshmentPointController.showModifyForm(id);
        if (dto != null) {
            this.requestDto = dto;
            displayForm(dto);
        } else {
            displayErrorMessage("Failed to load refreshment point data.");
        }
    }

    public void displayForm(ModifyRefreshmentPointRequestDto dto) {
        System.out.println("\n=== Refreshment Point Modification Form ===");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Current Location: " + dto.getLocation());
        System.out.println("Current Operating Hours: " + dto.getOperatingHours());
        System.out.println("Current Contact Info: " + dto.getContactInfo());
        System.out.println("Please enter new values (press Enter to keep current):");
    }

    /**
     * Step 3: Actor modifies data and submits.
     */
    public void onFormSubmit(ModifyRefreshmentPointRequestDto updatedData) {
        this.requestDto = updatedData;
        ResultDto result = refreshmentPointController.submitModification(updatedData);
        if (result.isSuccess()) {
            // Step 5: Ask for confirmation
            boolean confirm = displayConfirmationPrompt();
            if (confirm) {
                // Step 5.1: Confirm and execute
                ResultDto confirmResult = refreshmentPointController.confirmModification(updatedData);
                if (confirmResult.isSuccess()) {
                    displaySuccessMessage();
                } else {
                    displayErrorMessage(confirmResult.getMessage());
                }
            } else {
                // Cancel operation
                abortModification();
            }
        } else {
            // Activate Error Flow (Errored Use Case)
            displayErrorMessage(result.getMessage());
        }
    }

    public boolean displayConfirmationPrompt() {
        System.out.print("Are you sure you want to modify? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    public void displaySuccessMessage() {
        System.out.println("Modification successful! The refreshment point has been updated.");
    }

    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    public void abortModification() {
        System.out.println("Operation cancelled.");
    }
}
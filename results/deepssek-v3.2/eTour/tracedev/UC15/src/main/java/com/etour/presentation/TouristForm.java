package com.etour.presentation;

import com.etour.dto.TouristDTO;
import com.etour.controller.ModifyTouristController;
import com.etour.controller.SearchTouristController;

import java.util.List;
import java.util.Scanner;

/**
 * Presentation layer form for tourist modification.
 * Interacts with the agency operator and controllers.
 */
public class TouristForm {
    private String touristId;
    private TouristDTO formData;
    private ModifyTouristController modifyController;
    private SearchTouristController searchController;
    private Scanner scanner;

    public TouristForm(ModifyTouristController modifyController, SearchTouristController searchController) {
        this.modifyController = modifyController;
        this.searchController = searchController;
        this.scanner = new Scanner(System.in);
        this.formData = new TouristDTO();
    }

    public void loadData(TouristDTO touristDto) {
        this.formData = touristDto;
        this.touristId = touristDto.getTouristId();
        System.out.println("Tourist data loaded for ID: " + touristId);
    }

    public void displayForm() {
        System.out.println("\n=== Tourist Modification Form ===");
        System.out.println("Tourist ID: " + (formData.getTouristId() != null ? formData.getTouristId() : "N/A"));
        System.out.println("Name: " + (formData.getName() != null ? formData.getName() : ""));
        System.out.println("Email: " + (formData.getEmail() != null ? formData.getEmail() : ""));
        System.out.println("Phone: " + (formData.getPhoneNumber() != null ? formData.getPhoneNumber() : ""));
        System.out.println("Other Attributes: " + formData.getOtherAttributes());
        System.out.println("===============================\n");
    }

    public TouristDTO getEditedData() {
        // Simulate editing via console input
        System.out.println("Editing tourist data (enter new values, press Enter to keep current):");
        System.out.print("Name [" + formData.getName() + "]: ");
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()) {
            formData.setName(name);
        }
        System.out.print("Email [" + formData.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) {
            formData.setEmail(email);
        }
        System.out.print("Phone [" + formData.getPhoneNumber() + "]: ");
        String phone = scanner.nextLine();
        if (!phone.trim().isEmpty()) {
            formData.setPhoneNumber(phone);
        }
        return formData;
    }

    public void submitForm() {
        System.out.println("Submitting form...");
        modifyController.submitForm();
    }

    public void showConfirmationPrompt() {
        System.out.println("Please confirm the modification (yes/no): ");
    }

    public void askConfirmation() {
        showConfirmationPrompt();
        String response = scanner.nextLine();
        if ("yes".equalsIgnoreCase(response)) {
            System.out.println("Confirmation received.");
            // The actual confirmation is handled by the controller via confirmModification
        } else {
            System.out.println("Modification cancelled.");
        }
    }

    public void showValidationErrors(List<String> errors) {
        System.out.println("Validation errors:");
        for (String error : errors) {
            System.out.println(" - " + error);
        }
    }

    public void showConnectionError() {
        System.out.println("ERROR: Connection to server ETOUR interrupted. Operation aborted.");
    }

    public void viewTouristList() {
        List<TouristDTO> list = fetchTouristList();
        System.out.println("\n=== List of Tourists ===");
        for (TouristDTO dto : list) {
            System.out.println(dto.getTouristId() + ": " + dto.getName() + " (" + dto.getEmail() + ")");
        }
        System.out.println("========================\n");
    }

    public void selectTourist(String touristId) {
        this.touristId = touristId;
        System.out.println("Selected tourist: " + touristId);
    }

    public void activateModifyFunction() {
        System.out.println("Modify function activated.");
    }

    public List<TouristDTO> fetchTouristList() {
        return searchController.getTouristList();
    }

    public void showLoginRequired() {
        System.out.println("ERROR: You must be logged in to perform this action.");
    }
}
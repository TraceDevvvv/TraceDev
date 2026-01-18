package com.example.presentation;

import com.example.application.ModifyTouristUseCaseController;
import com.example.application.ModifyTouristRequest;
import com.example.application.ModifyTouristResponse;
import com.example.domain.Tourist;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Presentation layer component that interacts with Agency Operator and Use Case Controller.
 */
public class TouristPresentation {
    private ModifyTouristUseCaseController useCaseController;
    private boolean loginStatus;
    private Scanner scanner;

    public TouristPresentation(ModifyTouristUseCaseController controller) {
        this.useCaseController = controller;
        this.loginStatus = false;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a list of tourists.
     */
    public void displayTouristList(List<Tourist> tourists) {
        System.out.println("=== Tourist List ===");
        for (Tourist t : tourists) {
            System.out.println("ID: " + t.getId() + ", Name: " + t.getName());
        }
        System.out.println();
    }

    /**
     * Displays a form for a given tourist and returns edited data.
     */
    public Map<String, Object> displayTouristForm(Tourist tourist) {
        System.out.println("=== Edit Tourist ===");
        System.out.println("Current Data: ID=" + tourist.getId() + ", Name=" + tourist.getName() +
                ", Email=" + tourist.getEmail() + ", Phone=" + tourist.getPhoneNumber());
        Map<String, Object> editedData = new HashMap<>();
        System.out.print("Enter new name (press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            editedData.put("name", name);
        }
        System.out.print("Enter new email (press Enter to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            editedData.put("email", email);
        }
        System.out.print("Enter new phone (press Enter to keep current): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            editedData.put("phoneNumber", phone);
        }
        return editedData;
    }

    /**
     * Displays a confirmation dialog and returns user's choice.
     */
    public boolean displayConfirmation() {
        System.out.print("Confirm changes? (yes/no): ");
        String input = scanner.nextLine().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    /**
     * Displays an error message.
     */
    public void displayError(String message) {
        System.err.println("ERROR: " + message);
    }

    /**
     * Displays a success message.
     */
    public void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Confirms operation (called by Agency Operator).
     */
    public boolean confirmOperation() {
        return displayConfirmation();
    }

    /**
     * Allows Agency Operator to correct errors.
     */
    public void correctErrors() {
        System.out.println("Please correct the errors and try again.");
    }

    /**
     * Checks if the Agency Operator is logged in.
     */
    public boolean isLoggedIn() {
        return loginStatus;
    }

    public void setLoginStatus(boolean status) {
        this.loginStatus = status;
    }

    /**
     * Activates the use case SearchTourist (m1).
     */
    public void activateUseCaseSearchTourist() {
        // Message m1 from AO to TP
        System.out.println("TouristPresentation: activate use case SearchTourist");
    }

    /**
     * Selects a tourist by ID (m9).
     */
    public void selectTourist(String id) {
        // Message m9 from AO to TP
        System.out.println("TouristPresentation: select tourist(" + id + ")");
    }

    /**
     * Returns display tourist list (m8).
     */
    public void returnDisplayTouristList() {
        // Message m8 from TP to AO
        System.out.println("TouristPresentation: display tourist list");
    }

    /**
     * Returns form with edited data (m19).
     */
    public Map<String, Object> returnFormWithEditedData() {
        // Message m19 from TP to AO
        System.out.println("TouristPresentation: form with edited data");
        Map<String, Object> data = new HashMap<>();
        return data;
    }

    /**
     * Returns display tourist form with loaded data (m17).
     */
    public void returnDisplayTouristFormWithLoadedData() {
        // Message m17 from TP to AO
        System.out.println("TouristPresentation: display tourist form with loaded data");
    }

    /**
     * Returns display error message (m25).
     */
    public void returnDisplayErrorMessage() {
        // Message m25 from TP to AO
        System.out.println("TouristPresentation: display error message");
    }

    /**
     * Returns display confirmation dialog (m29).
     */
    public void returnDisplayConfirmationDialog() {
        // Message m29 from TP to AO
        System.out.println("TouristPresentation: display confirmation dialog");
    }

    /**
     * Returns confirmation received (m31).
     */
    public void returnConfirmationReceived() {
        // Message m31 from TP to UC
        System.out.println("TouristPresentation: confirmation received");
    }

    /**
     * Returns display connection error (m37).
     */
    public void returnDisplayConnectionError() {
        // Message m37 from TP to AO
        System.out.println("TouristPresentation: display connection error");
    }

    /**
     * Returns display success message (m48).
     */
    public void returnDisplaySuccessMessage() {
        // Message m48 from TP to AO
        System.out.println("TouristPresentation: display success message");
    }

    /**
     * Main interaction method simulating the sequence diagram flow.
     */
    public void runModifyTouristFlow() {
        // Simulate Agency Operator logged in
        setLoginStatus(true);
        if (!isLoggedIn()) {
            System.out.println("Please log in first.");
            return;
        }

        // Flow 1: SearchTourist
        System.out.println("Searching tourists...");
        List<Tourist> tourists = useCaseController.searchTourist(new HashMap<>());
        displayTouristList(tourists);

        // Select tourist
        System.out.print("Enter tourist ID to modify: ");
        String id = scanner.nextLine();
        Tourist selected = useCaseController.findById(id);
        if (selected == null) {
            displayError("Tourist not found.");
            return;
        }

        // Display form and edit
        Map<String, Object> editedData = displayTouristForm(selected);

        // Submit form
        if (editedData.isEmpty()) {
            System.out.println("No changes made.");
            return;
        }
        ModifyTouristRequest request = new ModifyTouristRequest(id, editedData);
        ModifyTouristResponse response = useCaseController.execute(request);

        if (response.isSuccess()) {
            displaySuccess(response.getMessage());
        } else {
            displayError(response.getMessage());
        }
    }

    // For testing without Scanner
    public void runSimulatedFlow() {
        setLoginStatus(true);
        System.out.println("=== Simulated Modify Tourist Flow ===");
        List<Tourist> tourists = useCaseController.searchTourist(new HashMap<>());
        displayTouristList(tourists);
        // Simulate selection of first tourist
        if (!tourists.isEmpty()) {
            Tourist selected = tourists.get(0);
            Map<String, Object> editedData = new HashMap<>();
            editedData.put("name", "Updated Name");
            editedData.put("email", "updated@example.com");
            ModifyTouristRequest request = new ModifyTouristRequest(selected.getId(), editedData);
            ModifyTouristResponse response = useCaseController.execute(request);
            if (response.isSuccess()) {
                displaySuccess(response.getMessage());
            } else {
                displayError(response.getMessage());
            }
        }
    }
}
package com.example.culturalgoods.ui;

import com.example.culturalgoods.dto.CulturalGoodDTO;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents the User Interface (UI) boundary for interacting with the Agency Operator.
 * This class simulates display and input functionalities.
 */
public class UI {
    private final Scanner scanner; // For simulating user input

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the form for entering cultural good data.
     * Implements `displayForm()` from the sequence diagram.
     */
    public void displayForm() {
        System.out.println("\n--- Cultural Good Insertion Form ---");
        System.out.println("Please fill in the details for the new cultural good:");
        // In a real UI, this would render input fields.
    }

    /**
     * Simulates getting form data from the user.
     * For this runnable example, it returns hardcoded data or can be expanded to take console input.
     * The sequence diagram implies the UI 'gets' data from AO, then passes a DTO to Controller.
     * For this simulation, the main method will simulate the AO filling the form by creating a DTO directly.
     * This method is kept as a placeholder but its direct call by controller is replaced by DTO creation in Main.
     * @return A CulturalGoodDTO with user-provided data.
     */
    public CulturalGoodDTO getFormData() {
        System.out.println(" (Simulating user input for DTO. For interactive input, uncomment scanner code.)");
        // Example for interactive input:
        // System.out.print("Name: ");
        // String name = scanner.nextLine();
        // System.out.print("Description: ");
        // String description = scanner.nextLine();
        // System.out.print("Acquisition Date (YYYY-MM-DD): ");
        // LocalDate acquisitionDate = LocalDate.parse(scanner.nextLine());
        // return new CulturalGoodDTO(name, description, acquisitionDate);

        // For non-interactive demonstration, this method won't be directly called as Main will build the DTO.
        // Returning a placeholder DTO just in case, but it's not the primary way data enters the flow in this simulation.
        return new CulturalGoodDTO("Placeholder Good", "A temporary placeholder good.", LocalDate.now());
    }

    /**
     * Displays a confirmation request to the user with the provided cultural good data.
     * Implements `showConfirmationDialog(dto : CulturalGoodDTO)` from the sequence diagram.
     * @param dto The CulturalGoodDTO to confirm.
     */
    public void showConfirmationDialog(CulturalGoodDTO dto) {
        System.out.println("\n--- Please Confirm Cultural Good Details ---");
        System.out.println("Name: " + dto.name);
        System.out.println("Description: " + dto.description);
        System.out.println("Acquisition Date: " + dto.acquisitionDate);
        System.out.println("Do you want to confirm this insertion? (Yes/No)");
    }

    /**
     * Displays a success message to the user.
     * Implements `showSuccessMessage(message : String)` from the sequence diagram.
     * @param message The success message to display.
     */
    public void showSuccessMessage(String message) {
        System.out.println("\n--- SUCCESS ---");
        System.out.println(message);
        System.out.println("-----------------");
    }

    /**
     * Displays an error message to the user.
     * Implements `showErrorMessage(error : String)` from the sequence diagram.
     * @param error The error message to display.
     */
    public void showErrorMessage(String error) {
        System.err.println("\n--- ERROR ---");
        System.err.println(error);
        System.err.println("---------------");
    }

    /**
     * Notifies the user that the operation has been cancelled.
     * Implements `notifyCancellation(message : String)` from the class diagram.
     * @param message The cancellation message to display.
     */
    public void notifyCancellation(String message) {
        System.out.println("\n--- CANCELLATION ---");
        System.out.println(message);
        System.out.println("--------------------");
    }

    /**
     * Reads a simple 'yes' or 'no' confirmation from the simulated user.
     * @return true if 'yes', false if 'no'.
     */
    public boolean getConfirmation() {
        // For demonstration, we'll assume 'yes' by default, or you can uncomment scanner.
        // System.out.print("Your choice: ");
        // String input = scanner.nextLine().trim().toLowerCase();
        // return input.equals("yes");
        System.out.println(" (Simulating user confirmation: Yes)");
        return true; // Assume confirmation for demonstration
    }

    /**
     * Reads a simple 'cancel' action from the simulated user.
     * @return true if 'cancel', false otherwise (e.g. for confirm).
     */
    public boolean getCancellationChoice() {
        // For demonstration, we'll assume 'no' (not cancelled) by default for the 'confirm' path
        // To simulate cancellation, you would set this to true in Main
        System.out.println(" (Simulating user cancellation choice: No)");
        return false;
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
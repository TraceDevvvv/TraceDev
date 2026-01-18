
package com.example.view;

import com.example.controller.DeleteCulturalHeritageController;
import com.example.dto.HeritageDTO;
import com.example.dto.OperationResult;
import java.util.List;
import java.util.Scanner;

/**
 * Boundary class for Cultural Heritage List View
 */
public class CulturalHeritageListView {
    private DeleteCulturalHeritageController controller;
    private Scanner scanner;
    private String selectedId;

    public CulturalHeritageListView(DeleteCulturalHeritageController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays list of cultural heritage items
     */
    public void displayHeritageList(List<HeritageDTO> items) {
        System.out.println("\n=== Cultural Heritage List ===");
        if (items.isEmpty()) {
            System.out.println("No cultural heritage items found.");
        } else {
            for (HeritageDTO item : items) {
                System.out.printf("ID: %s | Name: %s | Location: %s%n", 
                    item.getId(), item.getName(), item.getLocation());
            }
        }
        System.out.println("=============================");
    }

    /**
     * Gets selected heritage ID from user
     */
    public String getSelectedId() {
        System.out.print("\nEnter the ID of the cultural heritage to delete: ");
        selectedId = scanner.nextLine();
        return selectedId;
    }

    /**
     * Shows confirmation dialog and returns user's choice
     */
    public boolean showConfirmationDialog() {
        System.out.print("\nAre you sure you want to delete cultural heritage with ID " + selectedId + "? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    /**
     * Shows success message
     */
    public void showSuccessMessage() {
        System.out.println("\n✓ Cultural heritage deleted successfully!");
    }

    /**
     * Shows error message
     */
    public void showErrorMessage() {
        System.out.println("\n✗ Failed to delete cultural heritage!");
    }

    /**
     * Main method to run the application
     */
    public void run() {
        try {
            // Step 1: Display heritage list
            List<HeritageDTO> heritageList = controller.getHeritageList();
            displayHeritageList(heritageList);

            if (!heritageList.isEmpty()) {
                // Step 2: Get user selection
                String selectedId = getSelectedId();

                // Step 3: Show confirmation dialog
                boolean confirmed = showConfirmationDialog();

                if (confirmed) {
                    // Step 4: Execute deletion
                    OperationResult result = controller.deleteHeritage(selectedId);

                    // Step 5: Show appropriate message
                    if (result.isSuccess()) {
                        showSuccessMessage();
                    } else {
                        System.out.println("\n✗ " + result.getMessage());
                    }
                } else {
                    System.out.println("\nOperation cancelled by user.");
                }
            }
        } catch (Exception e) {
            System.out.println("\n✗ Error: " + e.getMessage());
        }
    }

    /**
     * Method to simulate the view interactions from sequence diagram
     */
    public void simulateSequenceFlow() {
        System.out.println("\n=== Simulating Sequence Diagram Flow ===");
        
        // Actor views list
        System.out.println("1. Agency Operator views Cultural Heritage List");
        List<HeritageDTO> list = controller.getHeritageList();
        displayHeritageList(list);
    }
}

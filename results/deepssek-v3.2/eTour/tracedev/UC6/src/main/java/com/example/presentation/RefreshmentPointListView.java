package com.example.presentation;

import com.example.application.DeleteRefreshmentPointUseCaseController;
import com.example.domain.AgencyOperator;
import com.example.dto.RefreshmentPointDTO;
import com.example.repository.IRefreshmentPointRepository;
import com.example.repository.RefreshmentPointRepositoryImpl;
import com.example.domain.RefreshmentPoint;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Boundary class representing the list view for refreshment points.
 * Simulates a simple console UI.
 */
public class RefreshmentPointListView {
    private List<RefreshmentPointDTO> refreshmentPoints;
    private DeleteRefreshmentPointUseCaseController controller;
    private AgencyOperator operator;
    private IRefreshmentPointRepository repository;
    private Scanner scanner;

    public RefreshmentPointListView() {
        this.repository = new RefreshmentPointRepositoryImpl();
        this.controller = new DeleteRefreshmentPointUseCaseController(repository);
        this.operator = new AgencyOperator();
        this.scanner = new Scanner(System.in);
        // Simulate login (required by entry conditions)
        operator.login("admin", "password");
    }

    /**
     * Displays the list of refreshment points (simulated UI).
     */
    public void displayList() {
        refreshmentPoints = repository.findAll().stream()
                .map(RefreshmentPointDTO::new)
                .collect(Collectors.toList());
        System.out.println("=== Refreshment Points ===");
        for (RefreshmentPointDTO dto : refreshmentPoints) {
            System.out.println("ID: " + dto.getId() + " | Name: " + dto.getName() + " | Location: " + dto.getLocation());
        }
        System.out.println("=========================");
    }

    /**
     * Shows a confirmation dialog for deletion.
     * @param pointId the ID of the point to delete
     * @return true if user confirms, false if cancels
     */
    public boolean showConfirmationDialog(String pointId) {
        System.out.print("Are you sure you want to delete refreshment point " + pointId + "? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    /**
     * Asks the user for confirmation (called by controller as per sequence diagram).
     * @return true if user confirms, false otherwise
     */
    public boolean askForConfirmation() {
        System.out.print("Confirm deletion? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    /**
     * Shows a success message after deletion.
     */
    public void showSuccessMessage() {
        System.out.println("Refreshment point deleted successfully!");
    }

    /**
     * Shows an error message.
     * @param message the error message to display
     */
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Shows operation cancelled message.
     */
    public void showOperationCancelled() {
        System.out.println("Operation cancelled.");
    }

    /**
     * Shows selected point details.
     */
    public void showSelectedPointDetails(RefreshmentPoint point) {
        System.out.println("Selected: " + point.getName() + " at " + point.getLocation() + " - " + point.getDescription());
    }

    /**
     * Main loop to simulate the UI flow based on the sequence diagram.
     */
    public void run() {
        if (!operator.isLoggedIn()) {
            System.out.println("Operator must be logged in.");
            return;
        }

        // Step 1: View list (from SearchCulturalHeritage)
        displayList();

        // Step 2: Select a refreshment point
        System.out.print("Enter the ID of the refreshment point to delete: ");
        String selectedPointId = scanner.nextLine().trim();
        RefreshmentPoint point = repository.findById(selectedPointId);
        if (point == null) {
            System.out.println("Refreshment point not found.");
            return;
        }
        showSelectedPointDetails(point);

        // Step 3: Activate delete function
        System.out.println("Activating delete function...");

        // Controller calls askForConfirmation (as per sequence diagram)
        boolean confirmed = askForConfirmation();
        if (!confirmed) {
            showOperationCancelled();
            return;
        }

        // Controller proceeds with deletion
        boolean success = controller.execute(selectedPointId);
        if (success) {
            showSuccessMessage();
        } else {
            showErrorMessage("Deletion failed due to connection error.");
        }
    }

    public static void main(String[] args) {
        RefreshmentPointListView view = new RefreshmentPointListView();
        view.run();
    }
}
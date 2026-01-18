package com.example.presentation;

import com.example.application.DeleteTouristCommand;
import com.example.application.DeleteTouristHandler;
import com.example.application.INotificationService;
import com.example.application.ITouristRepository;
import com.example.application.IUnitOfWork;
import com.example.domain.Tourist;
import com.example.dto.CommandResult;
import com.example.dto.TouristDTO;
import com.example.infrastructure.AuthenticationService;
import com.example.infrastructure.EmailNotificationService;
import com.example.infrastructure.TouristRepository;
import com.example.infrastructure.UnitOfWork;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Presentation Layer - Displays confirmation UI.
 * Simulates user interactions via console for demonstration.
 */
public class DeleteTouristConfirmationView {
    private List<TouristDTO> touristList = new ArrayList<>();
    private int selectedTouristId = -1;
    private Scanner scanner = new Scanner(System.in);

    // Dependencies (in a real app these would be injected)
    private ITouristRepository touristRepository = new TouristRepository();
    private IUnitOfWork unitOfWork = new UnitOfWork();
    private INotificationService notificationService = new EmailNotificationService();
    private AuthenticationService authenticationService = new AuthenticationService();
    private DeleteTouristHandler handler;

    public DeleteTouristConfirmationView() {
        this.handler = new DeleteTouristHandler(touristRepository, unitOfWork, notificationService);
        loadTouristList();
    }

    /**
     * Loads tourist list via SearchTourist use case (Step 1).
     */
    public void loadTouristList() {
        // Convert domain tourists to DTOs
        List<Tourist> domainTourists = touristRepository.getAll();
        touristList.clear();
        for (Tourist t : domainTourists) {
            touristList.add(new TouristDTO(t.getId(), t.getName(), t.getEmail()));
        }
    }

    public void setSelectedTouristId(int id) {
        this.selectedTouristId = id;
    }

    public int getSelectedTouristId() {
        return selectedTouristId;
    }

    public void renderConfirmation(TouristDTO touristDetails) {
        System.out.println("=== Delete Tourist Confirmation ===");
        System.out.println("Tourist ID: " + touristDetails.getId());
        System.out.println("Name: " + touristDetails.getName());
        System.out.println("Email: " + touristDetails.getEmail());
        System.out.println("Are you sure you want to delete this tourist? (yes/no)");
    }

    public void showSuccessNotification() {
        System.out.println("SUCCESS: Tourist account deleted successfully. Notification sent.");
    }

    public void showErrorNotification(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Asks user for confirmation (Step 3).
     * @return true if user confirms, false if cancels
     */
    public boolean askConfirmation() {
        if (selectedTouristId <= 0) {
            showErrorNotification("No tourist selected.");
            return false;
        }

        // Find the selected tourist DTO
        TouristDTO selected = null;
        for (TouristDTO dto : touristList) {
            if (dto.getId() == selectedTouristId) {
                selected = dto;
                break;
            }
        }

        if (selected == null) {
            showErrorNotification("Selected tourist not found in list.");
            return false;
        }

        renderConfirmation(selected);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    /**
     * Simulates the confirm button click (Step 4).
     */
    public void onConfirmClicked() {
        // Entry condition check (from sequence diagram)
        if (!authenticationService.isLoggedIn()) {
            showErrorNotification("Operator not logged in.");
            return;
        }

        // Create command (Step 5)
        DeleteTouristCommand command = new DeleteTouristCommand(selectedTouristId, authenticationService.getCurrentOperatorId());

        // Handle command
        CommandResult result = handler.handle(command);

        // Show result
        if (result.isSuccess()) {
            showSuccessNotification();
            // Refresh list after deletion
            loadTouristList();
        } else {
            showErrorNotification(result.getMessage());
        }
    }

    /**
     * Simulates the cancel button click.
     */
    public void onCancelClicked() {
        System.out.println("Operation cancelled.");
        selectedTouristId = -1;
    }

    /**
     * Main interaction loop for demonstration.
     */
    public void start() {
        System.out.println("=== Agency Operator Console ===");
        System.out.println("Loaded " + touristList.size() + " tourists.");

        // Display list
        for (TouristDTO dto : touristList) {
            System.out.println(dto.getId() + ": " + dto.getName() + " (" + dto.getEmail() + ")");
        }

        // Simulate selection (Step 1)
        System.out.print("\nEnter Tourist ID to delete (or 0 to exit): ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (id == 0) {
            System.out.println("Exiting.");
            return;
        }

        setSelectedTouristId(id);

        // Ask confirmation (Step 3)
        boolean confirmed = askConfirmation();

        if (confirmed) {
            onConfirmClicked();
        } else {
            onCancelClicked();
        }
    }

    // For demonstration purposes, a main method to run the scenario
    public static void main(String[] args) {
        DeleteTouristConfirmationView view = new DeleteTouristConfirmationView();
        view.start();
    }
}
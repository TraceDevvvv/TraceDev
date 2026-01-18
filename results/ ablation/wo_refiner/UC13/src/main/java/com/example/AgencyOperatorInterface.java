package com.example;

import java.util.List;
import java.util.Scanner;

/**
 * User interface for the agency operator.
 * Simulates console interaction.
 */
public class AgencyOperatorInterface {
    private ToggleTouristAccountStatusUseCase useCase;
    private TouristRepository repository;
    private Scanner scanner;

    public AgencyOperatorInterface(ToggleTouristAccountStatusUseCase useCase,
                                   TouristRepository repository) {
        this.useCase = useCase;
        this.repository = repository;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the list of tourists (active and inactive).
     */
    public List<Tourist> displayTouristList() {
        System.out.println("=== Tourist List ===");
        System.out.println("Active Tourists:");
        List<Tourist> active = repository.getActiveTourists();
        for (Tourist t : active) {
            System.out.println("  ID: " + t.getId() + ", Name: " + t.getName() + " [ACTIVE]");
        }
        System.out.println("Inactive Tourists:");
        List<Tourist> inactive = repository.getInactiveTourists();
        for (Tourist t : inactive) {
            System.out.println("  ID: " + t.getId() + ", Name: " + t.getName() + " [INACTIVE]");
        }
        return active; // Returning active list for simplicity, but method returns List<Tourist>
    }

    /**
     * Prompts the operator for confirmation of an action.
     */
    public boolean promptForConfirmation(String action) {
        System.out.print("Confirm " + action + "? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    public void showSuccessMessage() {
        System.out.println("Operation completed successfully.");
    }

    public void showErrorMessage() {
        System.out.println("Operation failed. Please check the error message.");
    }

    /**
     * Toggles tourist status directly (provided for completeness).
     */
    public void toggleTouristStatus(int touristId, boolean targetStatus) {
        ToggleTouristStatusCommand command = new ToggleTouristStatusCommand(touristId, targetStatus);
        Result result = useCase.execute(command);
        if (result.isSuccess()) {
            showSuccessMessage();
        } else {
            System.out.println("Error: " + result.getMessage());
            showErrorMessage();
        }
    }

    /**
     * Handles tourist selection and initiates the toggle process.
     */
    public void handleTouristSelection(int touristId, String action) {
        boolean targetStatus = action.equalsIgnoreCase("activate");
        System.out.println("Selected Tourist ID: " + touristId + " for " + action);
        toggleTouristStatus(touristId, targetStatus);
    }

    public void selectTouristForActivationDeactivation() {
        System.out.println("AO -> UI: Select tourist for activation/deactivation");
    }

    public void selectTouristAndAction() {
        System.out.println("AO -> UI: Select tourist and action (activate/deactivate)");
    }

    public void confirmOperation() {
        System.out.println("AO -> UI: Confirm operation");
    }

    public void cancelOperation() {
        System.out.println("AO -> UI: Cancel operation");
    }

    public void operationCancelled() {
        System.out.println("UI -> AO: Operation cancelled");
    }

    /**
     * Main interaction loop (simulates the sequence diagram).
     */
    public void start() {
        System.out.println("=== Agency Operator Interface ===");
        displayTouristList();

        selectTouristForActivationDeactivation();
        System.out.print("Enter Tourist ID to activate/deactivate: ");
        int touristId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        selectTouristAndAction();
        System.out.print("Enter action (activate/deactivate): ");
        String action = scanner.nextLine().trim();

        confirmOperation();
        boolean confirmed = promptForConfirmation(action);
        if (!confirmed) {
            cancelOperation();
            operationCancelled();
            System.out.println("Operation cancelled by user.");
            return;
        }

        handleTouristSelection(touristId, action);
    }

    public static void main(String[] args) {
        // Setup dependencies as per the diagrams
        ETOURServerConnection connection = new ETOURServerConnection();
        TouristRepository repository = new TouristRepositoryImpl(connection);
        NotificationService notificationService = new NotificationServiceImpl();
        ErrorHandler errorHandler = new ErrorHandler();

        // Establish connection as per sequence diagram
        connection.connect();

        ToggleTouristAccountStatusUseCase useCase = new ToggleTouristAccountStatusUseCase(
                repository, notificationService, errorHandler, connection);

        AgencyOperatorInterface ui = new AgencyOperatorInterface(useCase, repository);
        ui.start();
    }
}
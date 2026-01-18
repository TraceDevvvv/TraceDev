package com.example.etour;

import com.example.etour.controllers.RefreshmentPointController;
import com.example.etour.entities.RefreshmentPoint;
import com.example.etour.framework.ETOURDataSource;
import com.example.etour.interfaceadapters.DeleteRefreshmentPointPresenter;
import com.example.etour.interfaceadapters.RefreshmentPointRepositoryImpl;
import com.example.etour.interfaces.RefreshmentPointRepository;
import com.example.etour.usecases.DeleteRefreshmentPointInputPort;
import com.example.etour.usecases.DeleteRefreshmentPointUseCase;
import com.example.etour.views.RefreshmentPointView;

import java.sql.SQLException;

/**
 * Main class to demonstrate the flow of the deletion use case.
 * This simulates the interaction between the actor (Agency Operator) and the system.
 */
public class Main {
    public static void main(String[] args) {
        // Simulate the entry condition: Agency Operator initiates deletion
        System.out.println("=== Agency Operator requests deletion ===");

        // Setup the data source (using in-memory H2 or similar in a real scenario)
        ETOURDataSource dataSource;
        try {
            // Example connection parameters - adjust as needed
            dataSource = new ETOURDataSource("jdbc:h2:mem:test", "sa", "");
        } catch (SQLException e) {
            System.err.println("Failed to create data source: " + e.getMessage());
            return;
        }

        // Create repository, presenter, use case, view, and controller
        RefreshmentPointRepository repository = new RefreshmentPointRepositoryImpl(dataSource);
        RefreshmentPointView view = new ConsoleRefreshmentPointView();
        DeleteRefreshmentPointPresenter presenter = new DeleteRefreshmentPointPresenter(view);
        DeleteRefreshmentPointInputPort useCase = new DeleteRefreshmentPointUseCase(repository, presenter);
        RefreshmentPointController controller = new RefreshmentPointController(useCase, view);

        // Simulate the main flow: Agency Operator calls onDeleteRequest
        String refreshmentPointId = "RP001";
        controller.onDeleteRequest(refreshmentPointId);

        // Simulate alternative flow: cancellation
        System.out.println("\n=== Alternative Flow: User Cancels ===");
        controller.onDeleteRequest("RP002");

        // Cleanup
        try {
            dataSource.close();
        } catch (SQLException e) {
            System.err.println("Error closing data source: " + e.getMessage());
        }
    }

    /**
     * A simple console implementation of RefreshmentPointView for demonstration.
     */
    static class ConsoleRefreshmentPointView implements RefreshmentPointView {
        @Override
        public boolean showConfirmation(String message) {
            System.out.println("Confirmation: " + message);
            // Simulate user input: true for main flow, false for alternative flow
            // In a real GUI, this would be a dialog box.
            return !message.contains("RP002"); // Return false for RP002 to simulate cancellation
        }

        @Override
        public void showSuccess(String message) {
            System.out.println("SUCCESS: " + message);
        }

        @Override
        public void showError(String message) {
            System.out.println("ERROR: " + message);
        }

        @Override
        public void showList() {
            System.out.println("Displaying refreshed list of refreshment points.");
        }

        @Override
        public void onCancel() {
            System.out.println("Operation cancelled by user.");
        }
        
        // New methods for sequence diagram traceability
        @Override
        public void displaySuccessNotification(String message) {
            System.out.println("DISPLAY SUCCESS NOTIFICATION: " + message);
        }
        
        @Override
        public void displayRefreshedList() {
            System.out.println("DISPLAY REFRESHED LIST: Showing updated list of refreshment points");
        }
        
        @Override
        public void displayCancellationMessage(String message) {
            System.out.println("DISPLAY CANCELLATION MESSAGE: " + message);
        }
        
        @Override
        public void displayErrorMessage(String message) {
            System.out.println("DISPLAY ERROR MESSAGE: " + message);
        }
    }
}
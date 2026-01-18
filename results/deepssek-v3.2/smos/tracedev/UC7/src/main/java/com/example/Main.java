package com.example;

import com.example.application.UserListController;
import com.example.application.ViewUserDetailsController;
import com.example.infrastructure.SqlUserRepository;
import com.example.ui.UserListView;
import com.example.ui.UserDetailsView;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Main class to demonstrate the runnable system.
 * Sets up dependencies and simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        // Setup infrastructure (using H2 in-memory database for example)
        // Using a generic DataSource since we cannot depend on H2.
        // In a real scenario, you would inject a concrete DataSource.
        DataSource dataSource = null; // Placeholder – you would configure a real DataSource.

        SqlUserRepository repository = new SqlUserRepository(dataSource);
        // In a real application, you would populate the database here.

        // Setup application layer controllers
        UserListController listController = new UserListController(repository);
        ViewUserDetailsController detailsController = new ViewUserDetailsController(repository);

        // Setup UI boundaries
        UserListView listView = new UserListView(listController);
        UserDetailsView detailsView = new UserDetailsView(detailsController);

        // Simulate Administrator interacting with UI (as per sequence diagram)
        System.out.println("--- Simulating User List View ---");
        listView.displayUserList();

        System.out.println("\n--- Simulating Details Click ---");
        // Simulate clicking "Details" for a user (userId assumed)
        String sampleUserId = "123";
        listView.onDetailsClick(sampleUserId);

        // In a full UI, the details view would be triggered by the list view.
        // For demonstration, we directly call displayUserDetails with a sample DTO.
        System.out.println("\n--- Simulating User Details View ---");
        com.example.application.UserDetailsDTO sampleDto = new com.example.application.UserDetailsDTO(
                "John", "Doe", "john.doe@example.com", "+1234567890", "johndoe", "additionalField");
        detailsView.displayUserDetails(sampleDto);

        System.out.println("\n--- Simulating Alternative Flow (Connection Error) ---");
        // Simulate a connection exception
        try {
            // This would be thrown by SqlUserRepository in case of SQLException with connection error.
            throw new com.example.infrastructure.SMOSConnectionException("Connection to the SMOS server interrupted.");
        } catch (com.example.infrastructure.RepositoryException e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println("UI displays error message to Administrator.");
        }
    }
}
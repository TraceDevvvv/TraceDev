package com.example.ui;

import com.example.application.UserListController;
import com.example.application.UserSummaryDTO;
import com.example.infrastructure.RepositoryException;
import java.util.List;

/**
 * UI Boundary for displaying a list of users.
 * Handles user interactions and navigation to details view.
 */
public class UserListView {
    private UserListController userListController;

    public UserListView(UserListController userListController) {
        this.userListController = userListController;
    }

    /**
     * Displays the list of users.
     * Called when the view is loaded.
     */
    public void displayUserList() {
        try {
            List<UserSummaryDTO> users = userListController.execute();
            // In a real UI, this would update the UI components.
            System.out.println("=== User List ===");
            for (UserSummaryDTO user : users) {
                System.out.println(user.getId() + ": " + user.getName() + " " + user.getSurname());
            }
        } catch (Exception e) {
            System.err.println("Error loading user list: " + e.getMessage());
        }
    }

    /**
     * Handles "Details" button click from the administrator.
     * Navigates to the user details view.
     * @param userId the ID of the user to view details for
     */
    public void onDetailsClick(String userId) {
        System.out.println("Navigating to details for user ID: " + userId);
        // Create details view and controller (as per sequence diagram)
        // In a real application, this would be injected or created via a factory.
        // For simplicity, we assume the infrastructure is set up elsewhere.
        // This method would typically trigger a new UI panel.
    }
}
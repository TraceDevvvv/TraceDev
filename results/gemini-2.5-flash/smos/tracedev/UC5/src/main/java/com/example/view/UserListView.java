package com.example.view;

import com.example.model.User;
import com.example.controller.UserListController;

import java.util.List;

/**
 * The View component in the MVC pattern for displaying user lists.
 * It's responsible for presenting data to the user and capturing user input.
 */
public class UserListView {
    // Controller dependency for handling user interactions.
    // In a real GUI application, this would be registered as an event listener.
    private UserListController controller;

    /**
     * Sets the controller for this view.
     * @param controller The UserListController instance.
     */
    public void setController(UserListController controller) {
        this.controller = controller;
    }

    /**
     * Simulates a user clicking the "User Manager" button.
     * This acts as the entry point from the UI to the controller.
     */
    public void handleUserManagerClick() {
        System.out.println("\n[UserListView] Admin clicks 'User Manager' button.");
        if (controller != null) {
            controller.handleUserManagerClick();
        } else {
            System.err.println("[UserListView] Error: Controller not set.");
        }
    }

    /**
     * Displays a list of users to the administrator.
     * This method is called by the controller to update the view.
     * @param users A list of User objects to display.
     */
    public void displayUsers(List<User> users) {
        System.out.println("\n[UserListView] Displaying user list:");
        if (users == null || users.isEmpty()) {
            System.out.println("  No users found.");
        } else {
            for (User user : users) {
                System.out.println("  - " + user);
            }
        }
        System.out.println("[UserListView] User list displayed to Admin.");
    }

    /**
     * Displays an error message to the administrator.
     * This method is called by the controller when an error occurs.
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n[UserListView] ERROR: " + message);
        System.err.println("[UserListView] Error message displayed to Admin.");
    }
}
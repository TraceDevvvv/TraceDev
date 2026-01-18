package com.example.app.presentation;

import com.example.app.application.CoutentiViewingService;
import com.example.app.domain.User;

import java.util.List;

/**
 * Represents the view that displays a list of users.
 * Added to satisfy R5: "System IS viewing the list of users."
 */
public class UserListView {
    private UserFormController userFormController; // Added to satisfy R6 - dependency
    private CoutentiViewingService coutentiViewingService; // Added to satisfy R4 - dependency

    public UserListView(CoutentiViewingService coutentiViewingService) {
        this.coutentiViewingService = coutentiViewingService;
    }

    /**
     * Sets the UserFormController. This is typically done via dependency injection.
     * @param userFormController The controller responsible for user form actions.
     */
    public void setUserFormController(UserFormController userFormController) {
        this.userFormController = userFormController;
    }

    /**
     * Handles the event when a 'New User' button is clicked on the user list view.
     * This triggers the creation of a new user.
     * Added to satisfy R6: "Administrator clicks on the 'New user' button."
     */
    public void handleNewUserButtonClick() {
        System.out.println("\n[UserListView] Administrator clicks 'New User' button.");
        if (userFormController != null) {
            userFormController.handleNewUserClick();
        } else {
            System.out.println("[UserListView] UserFormController is not set. Cannot open new user form.");
        }
    }

    /**
     * Refreshes the displayed list of users.
     * In a real application, this would fetch the latest user data and update the UI.
     */
    public void refreshUserList() {
        System.out.println("\n[UserListView] Refreshing user list...");
        // In a real application, this would fetch users from a service and then call displayUserList.
        // For simulation, we'll just print a message.
        System.out.println("[UserListView] User list refreshed (UI event).");
        // ULS --> Admin : userListUpdated() in SD
        System.out.println("[Admin] User list updated.");
    }

    /**
     * Displays a list of users.
     *
     * @param users The list of User objects to display.
     */
    public void displayUserList(List<User> users) {
        System.out.println("\n--- Current User List ---");
        if (users.isEmpty()) {
            System.out.println("No users registered yet.");
        } else {
            for (User user : users) {
                System.out.println("  - Login: " + user.getLogin() + ", Name: " + user.getName() + " " + user.getSurname());
            }
        }
        System.out.println("-------------------------");
    }

    /**
     * Simulates the administrator viewing "Coutenti".
     * This method fulfills the R4 requirement.
     */
    public void viewCoutenti() {
        System.out.println("\n[Admin] Administrator is viewing the Coutenti.");
        coutentiViewingService.viewCoutenti();
    }
}
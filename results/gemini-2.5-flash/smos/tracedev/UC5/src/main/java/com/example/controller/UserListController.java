package com.example.controller;

import com.example.auth.AuthService;
import com.example.exception.ConnectionFailedException;
import com.example.model.User;
import com.example.model.UserListModel;
import com.example.service.UserService;
import com.example.view.UserListView;

import java.util.List;

/**
 * The Controller component in the MVC pattern for managing user list interactions.
 * It handles user input, orchestrates data retrieval and updates the model and view.
 */
public class UserListController {
    private UserService userService;
    private UserListModel userListModel;
    private UserListView userListView;
    private AuthService authService; // Added to satisfy requirement R3

    /**
     * Constructs a new UserListController.
     * All dependencies are injected via the constructor following good pract.
     * @param userService The service layer for user-related operations.
     * @param userListModel The model holding the user list data.
     * @param userListView The view for displaying user information and errors.
     * @param authService The authentication service for access control (R3).
     */
    public UserListController(UserService userService, UserListModel userListModel, UserListView userListView, AuthService authService) {
        this.userService = userService;
        this.userListModel = userListModel;
        this.userListView = userListView;
        this.authService = authService;
    }

    /**
     * Handles the event when the "User Manager" button is clicked.
     * This method orchestrates the display of users or an error message based on
     * administrator login status and data retrieval success.
     */
    public void handleUserManagerClick() {
        System.out.println("[UserListController] Handling 'User Manager' click.");
        // Pre-condition check for R3: Ensure administrator is logged in.
        if (authService.isAdministratorLoggedIn()) {
            refreshUserList();
        } else {
            // Administrator is NOT logged in (R3)
            userListView.showErrorMessage("Access Denied: Administrator not logged in.");
        }
    }

    /**
     * Refreshes the list of users displayed in the view.
     * This method fetches users, updates the model, and then updates the view.
     * It also handles potential connection failures.
     */
    public void refreshUserList() {
        System.out.println("[UserListController] Refreshing user list.");
        try {
            // Controller -> Service : getAllUsers()
            List<User> users = userService.getAllUsers();

            // Controller -> UserListModel : setUsers(userList)
            userListModel.setUsers(users);

            // Controller -> View : displayUsers(userList)
            userListView.displayUsers(users);
        } catch (ConnectionFailedException e) {
            // Connection to SMOS server interrupted (Alternative Exit Condition)
            System.err.println("[UserListController] Caught exception: " + e.getMessage());
            userListView.showErrorMessage("Connection to SMOS server interrupted.");
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("[UserListController] An unexpected error occurred: " + e.getMessage());
            userListView.showErrorMessage("An unexpected error occurred: " + e.getMessage());
        }
        System.out.println("[UserListController] User list refresh complete.");
    }
}
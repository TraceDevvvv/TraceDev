package com.example.app.controller;

import com.example.app.dto.UserDTO;
import com.example.app.exception.ConnectionError;
import com.example.app.service.UserApplicationService;
import com.example.app.view.UserDetailView;

/**
 * Controller for managing user detail requests.
 * It mediates between the UserDetailView and UserApplicationService.
 * Handles user interactions and orchestrates data retrieval and display.
 */
public class UserDetailController {
    // Composition: UserDetailController 'interactsWith' UserDetailView
    // The controller is responsible for creating and owning its view.
    private final UserDetailView userDetailView;
    // Association: UserDetailController 'uses' UserApplicationService
    private final UserApplicationService userApplicationService;

    /**
     * Constructs a UserDetailController with its required dependencies.
     * The controller also creates its associated UserDetailView, establishing composition.
     * @param userApplicationService The application service for user-related business logic.
     */
    public UserDetailController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
        // The controller creates its view and passes itself as the controller for the view,
        // establishing the "interactsWith" (composition) relationship and handling circular dependency.
        this.userDetailView = new UserDetailView(this);
    }

    /**
     * Provides access to the associated UserDetailView instance.
     * This is primarily for the Main class to trigger UI events on the view.
     * @return The UserDetailView instance managed by this controller.
     */
    public UserDetailView getUserDetailView() {
        return userDetailView;
    }

    /**
     * Handles the event when the 'Details' button is clicked for a specific user.
     * This method orchestrates the retrieval and display of user details or an error message.
     * @param selectedUserId The ID of the user whose details are requested.
     */
    public void onDetailsButtonClick(String selectedUserId) {
        System.out.println("\nUserDetailController: Handling details button click for ID: " + selectedUserId);
        try {
            // Delegate to UserApplicationService to get user details
            UserDTO userDTO = userApplicationService.getUserDetails(selectedUserId);

            // If successful, render details in the view
            if (userDTO != null) {
                userDetailView.renderUserDetails(userDTO);
            } else {
                // This scenario might happen if user not found, but no ConnectionError.
                userDetailView.displayMessage("User with ID '" + selectedUserId + "' not found.");
            }
        } catch (ConnectionError e) {
            // If a connection error occurs during data retrieval
            System.err.println("UserDetailController: Caught ConnectionError: " + e.getMessage());
            // Display an error message in the view
            userDetailView.displayMessage("Connection to SMOS server interrupted. Please try again later.");
        }
    }
}
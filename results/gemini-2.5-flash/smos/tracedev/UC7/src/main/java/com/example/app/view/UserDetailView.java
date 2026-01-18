package com.example.app.view;

import com.example.app.dto.UserDTO;
import com.example.app.controller.UserDetailController;

/**
 * Represents the presentation layer component for displaying user details.
 * This class is responsible for rendering information to the user and capturing UI events.
 * It maintains a reference to its controller for event handling.
 */
public class UserDetailView {

    // Association: UserDetailView has a reference to its controller to send events
    private UserDetailController controller;
    
    /**
     * Constructs a UserDetailView, injecting its controller dependency.
     * @param controller The controller this view interacts with for handling UI events.
     */
    public UserDetailView(UserDetailController controller) {
        this.controller = controller;
    }

    /**
     * Displays a general message to the user, typically for error or informational purposes.
     * @param message The message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println("\n--- User Detail View Message ---");
        System.out.println("MESSAGE: " + message);
        System.out.println("--------------------------------");
    }

    /**
     * Renders the detailed information of a user using a UserDTO.
     * Displays Name, Surname, E-mail, Cell, Login, and Password as specified in the Sequence Diagram.
     * @param userDTO The UserDTO containing the user details to be rendered.
     */
    public void renderUserDetails(UserDTO userDTO) {
        System.out.println("\n--- User Details ---");
        if (userDTO != null) {
            System.out.println(userDTO.toString());
        } else {
            System.out.println("No user details to display (UserDTO was null).");
        }
        System.out.println("--------------------");
    }

    /**
     * Simulates a user clicking a "Details" button for a specific user ID.
     * This method acts as an entry point from the UI to the controller.
     * It delegates the action to the associated controller.
     * @param selectedUserId The ID of the user selected by the administrator.
     */
    public void clicksDetailsButton(String selectedUserId) {
        System.out.println("\nUserDetailView: Administrator clicks 'Details' for User ID: " + selectedUserId);
        // External event triggering internal view logic. (R4)
        if (controller != null) {
            controller.onDetailsButtonClick(selectedUserId);
        } else {
            System.err.println("UserDetailView: Error - Controller not initialized. Cannot process button click.");
        }
    }
}
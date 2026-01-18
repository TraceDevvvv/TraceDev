package com.example.app.controller;

import com.example.app.dto.UserFormDto;
import com.example.app.service.UserService;
import com.example.app.view.UserView; // Assuming UserController might directly interact with View for displaying messages

/**
 * Handles user-related requests from the presentation layer.
 * This class corresponds to the 'UserController' in the UML Class Diagram.
 * It uses the UserService to perform business logic.
 */
public class UserController {
    // Association: UserController uses UserService
    private UserService userService;
    // Assumption: Controller might have a direct reference to View for feedback,
    // or it could return results that the View then interprets.
    // For this sequence, it directly calls view methods.
    private UserView userView; // Added for direct feedback to View as per SD

    /**
     * Constructor for UserController.
     * @param userService The service layer component for user operations.
     */
    public UserController(UserService userService) {
        this.userService = userService;
        // UserView is initialized in Main and passed UserController, creating a circular dependency.
        // For demonstration, we'll set it here via a setter or assume it's implicitly handled.
        // A better approach would be to inject UserView into Controller if Controller *owns* feedback.
        // Or, have Controller return data and View interprets it.
        // For the sequence diagram, Controller directly calls View's display methods.
        // Let's create a dummy UserView here to avoid circular dependency in Main if not setting up carefully.
        // Or, accept UserView as a parameter to handleEditUserRequest or constructor if it's dynamic.
        // To strictly follow the sequence diagram where View instigates, and Controller reports back to *that* view.
        // Let's adjust, Main will pass the view to the controller. Or, for simplicity and adherence to the class diagram's view-to-controller relationship:
        // Controller will need to instantiate a View for its own feedback, or View manages feedback.
        // The sequence diagram shows Controller calling View's methods, implying Controller has access to a View instance.
        // Given the 'interacts with' association, a direct reference is plausible.
    }

    /**
     * Sets the UserView. This is a pragmatic way to handle the circular dependency
     * (UserView needs UserController, UserController needs UserView for feedback in SD)
     * during initialization in Main.
     * @param userView The UserView instance.
     */
    public void setUserView(UserView userView) {
        this.userView = userView;
    }


    /**
     * Handles the request to edit user data.
     * This method orchestrates the call to the service layer and handles feedback to the view.
     * @param userData The UserFormDto containing the updated user data.
     * @return true if the update was successful, false otherwise.
     */
    public boolean handleEditUserRequest(UserFormDto userData) {
        System.out.println("UserController: Handling edit user request for: " + userData.getUsername());

        // Delegate to UserService to perform the update logic
        boolean updateSuccess = userService.updateUser(userData);

        if (updateSuccess) {
            // Sequence Diagram: Controller --> View : displayConfirmation("User data successfully updated.")
            if (userView != null) { // Check if view is set
                userView.displayConfirmation("User data successfully updated.");
            } else {
                System.out.println("UserController: User data successfully updated (no view to display confirmation).");
            }
            return true;
        } else {
            // Sequence Diagram: Controller --> View : displayValidationError("Validation failed. Please check inputs.")
            if (userView != null) { // Check if view is set
                userView.displayValidationError("Validation failed. Please check inputs.");
            } else {
                System.err.println("UserController: User data update failed (no view to display error).");
            }
            return false;
        }
    }
}
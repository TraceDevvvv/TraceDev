package com.example.app.view;

import com.example.app.controller.UserController;
import com.example.app.dto.UserFormDto;

/**
 * Represents the User Interface (UI) component for displaying user details and handling input.
 * This class corresponds to the 'UserView' in the UML Class Diagram.
 * It interacts with the UserController.
 */
public class UserView {
    // Association: UserView interacts with UserController
    private UserController userController;

    /**
     * Constructor for UserView.
     * @param userController The controller responsible for handling user-related actions.
     */
    public UserView(UserController userController) {
        this.userController = userController;
    }

    /**
     * Displays user details to the user, fulfilling R5.
     * @param userData The DTO containing user data to be displayed.
     */
    public void displayUserDetails(UserFormDto userData) {
        System.out.println("UserView: Displaying User Details:");
        System.out.println("  ID: " + userData.getId());
        System.out.println("  Username: " + userData.getUsername());
        System.out.println("  Email: " + userData.getEmail());
        System.out.println("  First Name: " + userData.getFirstName());
        System.out.println("  Last Name: " + userData.getLastName());
    }

    /**
     * Simulates getting user input from a form.
     * For this example, it returns a hardcoded DTO with updated data.
     * @return A UserFormDto populated with simulated user input.
     */
    public UserFormDto getUserInput() {
        System.out.println("UserView: Simulating Administrator modifying fields...");
        // This DTO simulates the data entered by the admin in the UI
        return new UserFormDto("user123", "janedoe", "jane.doe@example.com", "Jane", "Doe");
    }

    /**
     * Simulates getting user input for display, typically from a repository or initial load.
     * @param userId The ID of the user whose details are to be displayed.
     * @return A UserFormDto containing details of the user.
     */
    public UserFormDto getUserInputForDisplay(String userId) {
        // In a real application, this would fetch data from the controller/service
        // For simulation, we return a DTO based on the initial user.
        return new UserFormDto(userId, "johndoe", "john.doe@example.com", "John", "Doe");
    }

    /**
     * Simulates the administrator modifying the user details form.
     * This method corresponds to the 'modifyUserDetailsForm(updatedData)' message from Admin to View in the Sequence Diagram.
     * @param updatedData The UserFormDto containing the data modified by the administrator.
     */
    public void modifyUserDetailsForm(UserFormDto updatedData) {
        System.out.println("UserView: Administrator submits modified user details form.");
        // Delegate to the controller to handle the request.
        // The controller will then call back displayConfirmation or displayValidationError on this view.
        userController.handleEditUserRequest(updatedData);
    }

    /**
     * Displays a validation error message to the administrator, fulfilling R8.
     * @param message The error message to display.
     */
    public void displayValidationError(String message) {
        System.err.println("UserView: ERROR - " + message);
        // This output fulfills the 'm11: View -> Admin : showErrorMessage("Error: Data validation failed.")' from the sequence diagram.
    }

    /**
     * Displays a confirmation message to the administrator, fulfilling R10.
     * @param message The confirmation message to display.
     */
    public void displayConfirmation(String message) {
        System.out.println("UserView: CONFIRMATION - " + message);
        // This output fulfills the 'm20: View -> Admin : showSuccessMessage("User information modified.")' from the sequence diagram.
    }
}
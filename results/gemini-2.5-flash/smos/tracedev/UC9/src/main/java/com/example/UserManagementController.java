package com.example;

import java.util.List;
import java.util.Optional;

/**
 * Handles user input and orchestrates interactions between the view and the service layer.
 */
public class UserManagementController {
    private UserManagementService userManagementService;
    private UserManagementView userManagementView; // Added to satisfy requirement R4, R7, R8, R9

    /**
     * Constructs a UserManagementController with injected dependencies.
     * @param userManagementService The service responsible for business logic.
     * @param userManagementView The view responsible for displaying information.
     */
    public UserManagementController(UserManagementService userManagementService, UserManagementView userManagementView) { // Modified to satisfy requirement R4, R7, R8, R9
        this.userManagementService = userManagementService;
        this.userManagementView = userManagementView;
    }

    /**
     * Displays the details of a specific user.
     * The method also returns the User object, aligning with the class diagram signature.
     * @param userId The ID of the user to display.
     * @return The User object if found, null otherwise.
     */
    public User displayUserDetails(String userId) {
        System.out.println("\\nUserManagementController: Request to display details for user ID: " + userId);
        Optional<User> userOptional = userManagementService.findUserById(userId);
        if (userOptional.isPresent()) {
            userManagementView.displayUserDetails(userOptional.get());
            return userOptional.get();
        } else {
            userManagementView.displayUserDetails(null); // Indicate user not found to the view
            return null;
        }
    }

    /**
     * Handles the request to delete a user.
     * This method orchestrates the deletion process and updates the view accordingly.
     * @param userId The ID of the user to delete.
     * @return A list of all users after the deletion attempt (on success), or a String error message (on failure).
     */
    public Object handleDeleteUserRequest(String userId) { // Changed return type to Object to accommodate List<User> or String as per sequence diagram m16
        System.out.println("\\nUserManagementController: Handling delete user request for ID: " + userId);

        // Call the service to perform the deletion
        boolean deletionSuccessful = userManagementService.deleteUser(userId);

        if (deletionSuccessful) {
            System.out.println("UserManagementController: Deletion of user " + userId + " was successful.");
            // Retrieve the updated list of users as per sequence diagram
            List<User> updatedUserList = userManagementService.getAllUsers();
            // Display the updated list in the view (R7)
            userManagementView.displayUserList(updatedUserList);
            return updatedUserList; // This aligns with the "displays updated user list" return to Administrator (via View)
        } else {
            System.out.println("UserManagementController: Deletion of user " + userId + " failed.");
            String errorMessage = "Deletion of user " + userId + " failed. Please check logs for details.";
            // Display an error message (R9 path in sequence diagram)
            userManagementView.displayErrorMessage(errorMessage);
            return errorMessage; // Return error string as per sequence diagram m16
        }
    }
}
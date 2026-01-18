package com.system;

import java.util.List;

/**
 * Boundary class for the User Manager UI component.
 * Implements the entry condition check for button availability.
 * REQ: Entry Conditions #2 - User Manager button IS available when userManagerButtonEnabled = true
 */
public class UserManagerBoundary {
    private boolean userManagerButtonEnabled;
    private Administrator admin;

    /**
     * Constructor initializes with a reference to the administrator.
     */
    public UserManagerBoundary(Administrator admin) {
        this.admin = admin;
        // Button enabled only if admin is authenticated
        this.userManagerButtonEnabled = admin != null && admin.isAuthenticated();
    }

    /**
     * Called when the user clicks the User Manager button.
     * Sequence diagram interaction: Admin -> UI : clicks User Manager button
     */
    public void handleUserManagerButtonClick() {
        if (!userManagerButtonEnabled) {
            displayErrorMessage("Button is disabled. Please log in.");
            return;
        }

        // Check authentication (Entry Condition)
        if (admin != null && admin.isAuthenticated()) {
            DisplayUserListController controller = new DisplayUserListController();
            List<User> users = controller.execute();
            if (users != null && !users.isEmpty()) {
                displayUserList(users);
            } else {
                displayErrorMessage("No users found or connection issue.");
            }
        } else {
            displayErrorMessage("Please log in first");
        }
    }

    /**
     * Displays the list of users to the administrator.
     * Sequence diagram interaction: UI -> Admin : display list of users
     */
    public void displayUserList(List<User> users) {
        System.out.println("=== User List ===");
        for (User user : users) {
            System.out.println("ID: " + user.getUserId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail());
        }
        System.out.println("=================");
    }

    /**
     * Displays an error message.
     * Sequence diagram interaction: UI -> Admin : show error message
     */
    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Returns the button enabled state.
     */
    public boolean isButtonEnabled() {
        return userManagerButtonEnabled;
    }

    /**
     * Updates the button state based on admin authentication.
     * Called by the login flow prior to main sequence.
     * Sequence diagram: Admin -> UI : set isLoggedIn = true
     */
    public void setButtonEnabled(boolean enabled) {
        this.userManagerButtonEnabled = enabled;
    }
}
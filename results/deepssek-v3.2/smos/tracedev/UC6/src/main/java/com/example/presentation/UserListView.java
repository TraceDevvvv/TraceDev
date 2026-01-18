package com.example.presentation;

import com.example.application.InsertUserController;

/**
 * Boundary class representing the list view of users.
 * Allows the administrator to view the user list and initiate creating a new user.
 */
public class UserListView {
    private InsertUserController insertUserController;

    public UserListView(InsertUserController insertUserController) {
        this.insertUserController = insertUserController;
    }

    /**
     * Displays the list of users.
     * Pre-condition: Result of use case "viewing theCoutenti".
     */
    public void displayUserList() {
        System.out.println("Displaying user list...");
    }

    /**
     * Allows the administrator to select a user by ID (for potential future actions).
     *
     * @param userId the ID of the user to select.
     */
    public void selectUser(Long userId) {
        System.out.println("User selected: " + userId);
    }

    /**
     * Shows the new user form.
     * This method is called when the administrator clicks "New User".
     */
    public void showNewUserForm() {
        System.out.println("Showing new user form...");
        NewUserForm form = new NewUserForm(insertUserController, this);
        form.displayForm();
    }

    /**
     * Called when the administrator views the user list (sequence diagram message).
     */
    public void viewsUserList() {
        System.out.println("Admin views user list...");
    }

    /**
     * Called when the administrator clicks "New User" button.
     */
    public void clicksNewUser() {
        System.out.println("Admin clicks New User...");
    }
}
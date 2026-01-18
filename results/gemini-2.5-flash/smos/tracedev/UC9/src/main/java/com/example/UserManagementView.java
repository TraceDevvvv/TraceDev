package com.example;

import java.util.List;

/**
 * Handles the display logic for user management, presenting information to the Administrator.
 * Added to satisfy requirements R4, R7, R8, R9.
 */
public class UserManagementView {

    /**
     * Displays a list of users.
     * @param users The list of users to display.
     */
    public void displayUserList(List<User> users) {
        System.out.println("\n--- Current User List ---");
        if (users == null || users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            users.forEach(System.out::println);
        }
        System.out.println("-------------------------");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!");
    }

    /**
     * Displays detailed information about a single user.
     * @param user The user whose details are to be displayed.
     */
    public void displayUserDetails(User user) {
        if (user != null) {
            System.out.println("\n--- User Details ---");
            System.out.println("ID: " + user.getUserId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("--------------------");
        } else {
            System.out.println("\n--- User Details ---");
            System.out.println("User details not found.");
            System.out.println("--------------------");
        }
    }
}
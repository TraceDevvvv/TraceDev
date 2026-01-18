package com.example.usermanagement;

import java.util.List;

/**
 * Main application class to simulate the ViewUserList use case.
 * This class orchestrates the interaction between the user interface (simulated)
 * and the business logic (UserService) to display a list of users to an administrator.
 */
public class UserManagementApp {

    /**
     * The main method, entry point of the application.
     * It simulates the scenario where an administrator views the user list.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("--- User Management System - View User List ---");

        // 1. Initialize the data repository and service layer.
        // In a real application, these might be injected via a dependency injection framework.
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        // Simulate different users attempting to view the list.

        // Scenario 1: Administrator attempts to view the user list.
        System.out.println("\n--- Scenario 1: Administrator views user list ---");
        // Precondition: The user is logged in as administrator.
        // We retrieve an existing administrator user from our simulated repository.
        User adminUser = userRepository.findByUsername("admin");

        if (adminUser != null) {
            System.out.println("Simulating login for: " + adminUser.getUsername() + " (Role: " + adminUser.getRole() + ")");
            // Precondition: The user clicks on the "User Manager" button.
            System.out.println("Simulating 'User Manager' button click...");

            try {
                // Event sequence: System searches for users in the archive and displays the list.
                List<User> users = userService.getAllUsersForAdmin(adminUser);

                // Postcondition: The system displays the list of users in the system.
                System.out.println("\n--- User List (for Administrator) ---");
                if (users.isEmpty()) {
                    System.out.println("No users found in the system.");
                } else {
                    users.forEach(user -> System.out.println("- " + user.getUsername() + " (ID: " + user.getId() + ", Role: " + user.getRole() + ")"));
                }
            } catch (SecurityException e) {
                // This block should ideally not be reached if adminUser is indeed an administrator.
                System.err.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("Configuration Error: " + e.getMessage());
            }
        } else {
            System.err.println("Error: Administrator user 'admin' not found in the repository. Cannot run scenario 1.");
        }

        // Scenario 2: Regular user attempts to view the user list (unauthorized access).
        System.out.println("\n--- Scenario 2: Regular user attempts to view user list ---");
        User regularUser = userRepository.findByUsername("john.doe");

        if (regularUser != null) {
            System.out.println("Simulating login for: " + regularUser.getUsername() + " (Role: " + regularUser.getRole() + ")");
            System.out.println("Simulating 'User Manager' button click...");

            try {
                userService.getAllUsersForAdmin(regularUser);
                // This line should not be reached if security is correctly implemented.
                System.out.println("Unexpected: Regular user was able to view the user list.");
            } catch (SecurityException e) {
                // Expected behavior: Regular user is denied access.
                System.out.println("Access Denied: " + e.getMessage());
                System.out.println("As expected, a regular user cannot view the user list.");
            } catch (IllegalArgumentException e) {
                System.err.println("Configuration Error: " + e.getMessage());
            }
        } else {
            System.err.println("Error: Regular user 'john.doe' not found in the repository. Cannot run scenario 2.");
        }

        // Scenario 3: Attempt with a non-existent user or null user (edge case).
        System.out.println("\n--- Scenario 3: Attempt with a null user ---");
        System.out.println("Simulating 'User Manager' button click with a null user...");
        try {
            userService.getAllUsersForAdmin(null);
            System.out.println("Unexpected: Null user was able to view the user list.");
        } catch (IllegalArgumentException e) {
            System.out.println("Handled Error: " + e.getMessage());
            System.out.println("As expected, a null user cannot be processed.");
        } catch (SecurityException e) {
            System.err.println("Unexpected Security Error: " + e.getMessage());
        }

        // Postcondition: Connection to the interrupted SMOS server.
        // This is a simulated external system interaction.
        System.out.println("\n--- System Message ---");
        System.out.println("Simulating: Connection to the interrupted SMOS server.");
        System.out.println("--- End of User Management System Simulation ---");
    }
}
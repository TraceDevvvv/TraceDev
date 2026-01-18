package com.smos.rolemanagement;

import com.smos.rolemanagement.model.Role;
import com.smos.rolemanagement.model.User;
import com.smos.rolemanagement.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class simulates the Administrator's console interface for managing user roles.
 * It orchestrates the interaction based on the provided use case.
 */
public class AdminRoleManagementConsole {

    private final UserService userService;
    private final Scanner scanner;
    private boolean isAdminLoggedIn = false; // Simulates admin login status
    private User currentlyViewedUser = null; // Stores the user whose details are being viewed

    /**
     * Constructor initializes the UserService and Scanner.
     */
    public AdminRoleManagementConsole() {
        this.userService = new UserService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        AdminRoleManagementConsole app = new AdminRoleManagementConsole();
        app.run();
    }

    /**
     * Orchestrates the flow of the role management process.
     */
    public void run() {
        System.out.println("--- SMOS User Role Management System ---");

        // Precondition 1: The user is logged in as an administrator
        simulateAdminLogin();
        if (!isAdminLoggedIn) {
            System.out.println("Administrator login failed. Exiting.");
            return;
        }

        // Loop to allow multiple role management operations
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View User Details (Precondition for role management)");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // Precondition 2: The user has carried out the case of use \"viewdetTailsente\"
                    // and the system is viewing the details of a user
                    viewUserDetails();
                    if (currentlyViewedUser != null) {
                        // Precondition 3: The user clicks on the \"User Roles\" button
                        // (Simulated by automatically proceeding after viewing details)
                        System.out.println("\n--- User Roles Button Clicked ---");
                        manageUserRoles(currentlyViewedUser);
                    }
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // Postcondition: The administrator interrupts the connection to the SMOS server interrupted
        System.out.println("\n--- Administrator session ended. Connection to SMOS server interrupted. ---");
        scanner.close(); // Close the scanner to release system resources
    }

    /**
     * Simulates an administrator login.
     * For this console application, any non-empty username and password will be considered valid.
     * In a real application, this would involve authentication against a user store.
     */
    private void simulateAdminLogin() {
        System.out.println("Simulating Administrator Login...");
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (!username.isEmpty() && !password.isEmpty()) {
            System.out.println("Admin login successful!");
            isAdminLoggedIn = true;
        } else {
            System.out.println("Admin login failed. Invalid credentials.");
            isAdminLoggedIn = false;
        }
    }

    /**
     * Simulates the 'viewdetTailsente' use case.
     * Prompts the admin for a user ID and displays their current details and roles.
     */
    private void viewUserDetails() {
        System.out.println("\n--- View User Details ---");
        System.out.println("Available User IDs: " + userService.getAllUserIds());
        System.out.print("Enter User ID to view details: ");
        String userId = scanner.nextLine();

        Optional<User> userOptional = userService.getUserDetails(userId);

        if (userOptional.isPresent()) {
            currentlyViewedUser = userOptional.get();
            System.out.println("\nDetails for User: " + currentlyViewedUser.getUsername() + " (ID: " + currentlyViewedUser.getUserId() + ")");
            System.out.println("Current Roles: " +
                               currentlyViewedUser.getRoles().stream()
                                       .map(Role::getRoleName)
                                       .collect(Collectors.joining(", ")));
        } else {
            System.out.println("User with ID '" + userId + "' not found.");
            currentlyViewedUser = null;
        }
    }

    /**
     * Manages roles for the currently viewed user.
     * This method implements the core logic of the 'Assign/RemoveRolesToAUser' use case.
     *
     * @param user The user whose roles are to be managed.
     */
    private void manageUserRoles(User user) {
        System.out.println("\n--- Assign/Remove Roles for User: " + user.getUsername() + " ---");

        // System 1. Displays the role management form
        displayRoleManagementForm(user);

        // User 2. Select the roles to be assigned or removed to the user
        Set<Role> desiredRoles = selectDesiredRoles();

        // User 3. Click on the \"Send\" button (simulated by pressing Enter after selection)
        System.out.println("\nPress Enter to confirm role changes...");
        scanner.nextLine(); // Consume the newline

        // System 4. Assign or remove the user's roles as indicated by the administrator
        boolean success = userService.updateUserRoles(user.getUserId(), desiredRoles);

        if (success) {
            // Postcondition: User roles are modified
            System.out.println("\nRoles updated successfully for user: " + user.getUsername());
            // Refresh the currently viewed user to reflect changes
            currentlyViewedUser = userService.getUserDetails(user.getUserId()).orElse(null);
            if (currentlyViewedUser != null) {
                System.out.println("New Roles: " +
                                   currentlyViewedUser.getRoles().stream()
                                           .map(Role::getRoleName)
                                           .collect(Collectors.joining(", ")));
            }
        } else {
            System.out.println("Failed to update roles for user: " + user.getUsername());
        }
    }

    /**
     * Displays the current roles of the user and all available roles.
     *
     * @param user The user whose roles are being displayed.
     */
    private void displayRoleManagementForm(User user) {
        System.out.println("Current Roles for " + user.getUsername() + ":");
        if (user.getRoles().isEmpty()) {
            System.out.println("  (No roles assigned)");
        } else {
            user.getRoles().forEach(role -> System.out.println("  - " + role.getRoleName() + " (ID: " + role.getRoleId() + ")"));
        }

        System.out.println("\nAvailable Roles:");
        userService.getAllAvailableRoles().forEach(role -> System.out.println("  - " + role.getRoleName() + " (ID: " + role.getRoleId() + ")"));

        System.out.println("\nInstructions: Enter the IDs of the roles you want the user to *have* (comma-separated).");
        System.out.println("              Roles not listed will be removed. Enter nothing to remove all roles.");
    }

    /**
     * Prompts the administrator to select desired roles and converts their input into a Set of Role objects.
     *
     * @return A Set of Role objects representing the administrator's desired roles for the user.
     */
    private Set<Role> selectDesiredRoles() {
        Set<Role> selectedRoles = new HashSet<>();
        System.out.print("Enter desired role IDs (e.g., R001, R003): ");
        String input = scanner.nextLine().trim();

        if (!input.isEmpty()) {
            Set<String> roleIds = Arrays.stream(input.split(","))
                                        .map(String::trim)
                                        .collect(Collectors.toSet());

            for (String roleId : roleIds) {
                Optional<Role> roleOptional = userService.getRoleById(roleId);
                if (roleOptional.isPresent()) {
                    selectedRoles.add(roleOptional.get());
                } else {
                    System.out.println("Warning: Role ID '" + roleId + "' is invalid and will be ignored.");
                }
            }
        }
        return selectedRoles;
    }
}

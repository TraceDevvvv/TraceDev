package com.example.usermanagement;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class to demonstrate the user management system,
 * simulating user input and administrator actions for creating a new user.
 */
public class UserManagementApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManagementService userManagementService = new UserManagementService();

    public static void main(String[] args) {
        System.out.println("--- User Management System Demo ---");

        // Precondition: The user is logged in as an administrator
        System.out.println("\nSimulating Administrator Login...");
        boolean isAdminLoggedIn = simulateAdminLogin();
        if (!isAdminLoggedIn) {
            System.out.println("Administrator login failed. Exiting application.");
            return;
        }
        System.out.println("Administrator logged in successfully.");

        // Precondition: The user has carried out the case of use "viewing theCoutenti"
        // and the system is viewing the list of users in the system
        System.out.println("\nSimulating 'viewing the list of users'...");
        userManagementService.viewUsersList();
        System.out.println("Administrator is now viewing the list of users.");

        // Precondition: The user clicks on the "New user" button
        System.out.println("\nAdministrator clicks on 'New user' button.");

        // Scenario 1: Successful user creation
        System.out.println("\n--- Scenario 1: Attempting to create a valid new user ---");
        simulateNewUserForm("John", "Doe", "john.doe@example.com", "1234567890", "johndoe", "Password123!", "Password123!");

        // Scenario 2: User creation with invalid data (e.g., invalid email, weak password)
        System.out.println("\n--- Scenario 2: Attempting to create a user with invalid data ---");
        simulateNewUserForm("Jane", "Smith", "invalid-email", "123", "janesmith", "weak", "weak");

        // Scenario 3: User creation with a login that is already taken
        System.out.println("\n--- Scenario 3: Attempting to create a user with an existing login ---");
        simulateNewUserForm("Alice", "Wonder", "alice.wonder@example.com", "9876543210", "johndoe", "AlicePass1!", "AlicePass1!");

        // Scenario 4: User creation with mismatched passwords
        System.out.println("\n--- Scenario 4: Attempting to create a user with mismatched passwords ---");
        simulateNewUserForm("Bob", "Builder", "bob.builder@example.com", "5551234567", "bobbuilder", "BobPass123!", "BobPassXYZ!");

        // Postcondition: The administrator interrupts the connection to the SMOS server
        System.out.println("\n--- Post-application actions ---");
        userManagementService.interruptSMOSConnection();
        System.out.println("Application demo finished.");

        scanner.close();
    }

    /**
     * Simulates the administrator login process.
     * In a real application, this would involve authentication against a user database.
     * For this demo, it's a simple placeholder.
     *
     * @return true if login is successful, false otherwise.
     */
    private static boolean simulateAdminLogin() {
        // For simplicity, we'll assume login is always successful for the demo.
        // In a real system, this would involve checking credentials.
        return true;
    }

    /**
     * Simulates the user filling the form and clicking the "Save" button.
     * It then calls the service layer to process the new user creation.
     *
     * @param name The first name of the user.
     * @param surname The last name of the user.
     * @param email The email address of the user.
     * @param cell The cell phone number of the user.
     * @param login The unique login username for the user.
     * @param password The password for the user's account.
     * @param confirmPassword The confirmation password.
     */
    private static void simulateNewUserForm(String name, String surname, String email, String cell, String login, String password, String confirmPassword) {
        System.out.println("System: Displays the user entry form.");
        System.out.println("User: Fills the fields of the form:");
        System.out.println("  Name: " + name);
        System.out.println("  Surname: " + surname);
        System.out.println("  E-mail: " + email);
        System.out.println("  Cell: " + cell);
        System.out.println("  Login: " + login);
        System.out.println("  Password: " + password);
        System.out.println("  Confirm password: " + confirmPassword);
        System.out.println("User: Clicks on the 'Save' button.");

        // System: Make checks on the validity of the data entered and enter the new user in the archive
        List<String> errors = userManagementService.addNewUser(name, surname, email, cell, login, password, confirmPassword);

        if (errors.isEmpty()) {
            // Postcondition: A new user has been created
            System.out.println("System: New user '" + login + "' successfully created.");
        } else {
            // Postcondition: Is notified the data error
            System.out.println("System: Data entry errors detected (Errodati use case activated):");
            for (String error : errors) {
                System.out.println("  - " + error);
            }
        }
        System.out.println("----------------------------------------------------");
    }
}
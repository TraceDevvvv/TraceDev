package RegistrationAtSite_1766402455;

import java.util.Scanner;

/**
 * Main application class to simulate the user registration process.
 * This class acts as the entry point, simulating user interaction
 * and utilizing the RegistrationService to handle the business logic.
 */
public class RegistrationApp {

    public static void main(String[] args) {
        // Create an instance of the RegistrationService
        RegistrationService registrationService = new RegistrationService();
        // Create a Scanner object to simulate user input from the console
        Scanner scanner = new Scanner(System.in);

        System.out.println("=======================================");
        System.out.println("Welcome to the User Registration System");
        System.out.println("=======================================");

        // Simulate the "Register" button click and viewing the form
        System.out.println("\nSystem: User clicked 'Register' button.");
        System.out.println("System: Displaying registration form.");
        System.out.println("Please fill in the following details:");

        // --- Scenario 1: Successful Registration ---
        System.out.println("\n--- Attempting Successful Registration ---");
        try {
            // Simulate user filling the form fields
            String name = "John";
            String surname = "Doe";
            String mobilePhone = "+1234567890";
            String email = "john.doe@example.com";
            String username = "johndoe";
            String password = "Password123!";
            String confirmPassword = "Password123!";

            System.out.println("User: Filling form with valid details...");
            System.out.println("User: Name: " + name + ", Surname: " + surname + ", Mobile: " + mobilePhone +
                               ", Email: " + email + ", Username: " + username + ", Password: " + password);

            // Simulate user submitting the form
            System.out.println("User: Submitting form...");
            User registeredUser = registrationService.registerUser(
                name, surname, mobilePhone, email, username, password, confirmPassword
            );

            // Postcondition: The user has requested a request to the system
            if (registeredUser != null) {
                System.out.println("System: Registration successful for user: " + registeredUser.getUsername());
                System.out.println("System: User details: " + registeredUser);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("System: Registration failed! " + e.getMessage());
        }

        // --- Scenario 2: Registration with Mismatched Passwords ---
        System.out.println("\n--- Attempting Registration with Mismatched Passwords ---");
        try {
            String name = "Jane";
            String surname = "Smith";
            String mobilePhone = "0987654321";
            String email = "jane.smith@example.com";
            String username = "janesmith";
            String password = "SecurePassword1";
            String confirmPassword = "SecurePassword2"; // Mismatched

            System.out.println("User: Filling form with mismatched passwords...");
            System.out.println("User: Name: " + name + ", Surname: " + surname + ", Mobile: " + mobilePhone +
                               ", Email: " + email + ", Username: " + username + ", Password: " + password +
                               ", Confirm Password: " + confirmPassword);

            System.out.println("User: Submitting form...");
            registrationService.registerUser(
                name, surname, mobilePhone, email, username, password, confirmPassword
            );
        } catch (IllegalArgumentException e) {
            System.err.println("System: Registration failed as expected! " + e.getMessage());
        }

        // --- Scenario 3: Registration with an Already Taken Username ---
        System.out.println("\n--- Attempting Registration with an Already Taken Username ---");
        try {
            String name = "Peter";
            String surname = "Jones";
            String mobilePhone = "1122334455";
            String email = "peter.jones@example.com";
            String username = "johndoe"; // Already taken by Scenario 1
            String password = "MyPassword123";
            String confirmPassword = "MyPassword123";

            System.out.println("User: Filling form with an existing username...");
            System.out.println("User: Name: " + name + ", Surname: " + surname + ", Mobile: " + mobilePhone +
                               ", Email: " + email + ", Username: " + username + ", Password: " + password);

            System.out.println("User: Submitting form...");
            registrationService.registerUser(
                name, surname, mobilePhone, email, username, password, confirmPassword
            );
        } catch (IllegalArgumentException e) {
            System.err.println("System: Registration failed as expected! " + e.getMessage());
        }

        // --- Scenario 4: Registration with Invalid Email Format ---
        System.out.println("\n--- Attempting Registration with Invalid Email Format ---");
        try {
            String name = "Alice";
            String surname = "Wonder";
            String mobilePhone = "5551234567";
            String email = "alice@invalid"; // Invalid email
            String username = "alicew";
            String password = "AlicePassword";
            String confirmPassword = "AlicePassword";

            System.out.println("User: Filling form with invalid email...");
            System.out.println("User: Name: " + name + ", Surname: " + surname + ", Mobile: " + mobilePhone +
                               ", Email: " + email + ", Username: " + username + ", Password: " + password);

            System.out.println("User: Submitting form...");
            registrationService.registerUser(
                name, surname, mobilePhone, email, username, password, confirmPassword
            );
        } catch (IllegalArgumentException e) {
            System.err.println("System: Registration failed as expected! " + e.getMessage());
        }

        // --- Scenario 5: Registration with Empty Fields ---
        System.out.println("\n--- Attempting Registration with Empty Name Field ---");
        try {
            String name = ""; // Empty name
            String surname = "Empty";
            String mobilePhone = "1231231234";
            String email = "empty@example.com";
            String username = "emptyuser";
            String password = "EmptyPassword";
            String confirmPassword = "EmptyPassword";

            System.out.println("User: Filling form with empty name...");
            System.out.println("User: Name: '" + name + "', Surname: " + surname + ", Mobile: " + mobilePhone +
                               ", Email: " + email + ", Username: " + username + ", Password: " + password);

            System.out.println("User: Submitting form...");
            registrationService.registerUser(
                name, surname, mobilePhone, email, username, password, confirmPassword
            );
        } catch (IllegalArgumentException e) {
            System.err.println("System: Registration failed as expected! " + e.getMessage());
        }

        System.out.println("\n=======================================");
        System.out.println("Registration simulation finished.");
        System.out.println("=======================================");

        // Postcondition: The user interrupts the connection to the SMOS server interrupted
        // This is simulated by the program ending.
        System.out.println("System: User interrupts connection (program terminates).");

        // Close the scanner
        scanner.close();
    }
}
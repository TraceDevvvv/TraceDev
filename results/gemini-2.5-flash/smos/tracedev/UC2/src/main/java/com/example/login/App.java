package com.example.login;

/**
 * Main application class to demonstrate the login system.
 * This class orchestrates the initial setup and triggers the scenarios
 * described in the sequence diagrams.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("--- Starting Login Application Demonstration ---");

        // 1. Initialize components
        LoginView loginView = new LoginView();
        AuthenticationService authenticationService = new AuthenticationService();
        LoginController loginController = new LoginController(loginView, authenticationService);

        // --- Scenario 1: Demonstrate "Registered user informed of an authentication error" sequence diagram ---
        System.out.println("\n--- SCENARIO 1: Demonstrating authentication error handling (from sequence diagram) ---");
        System.out.println("    (This simulates a direct call to handleAuthenticationError, perhaps after a prior failed attempt.)");
        // Simulate a situation where an authentication error has already occurred
        // and the controller needs to inform the user and redisplay the form.
        loginController.handleAuthenticationError("Incorrect username or password.");
        System.out.println("--- End of SCENARIO 1 ---");

        // --- Scenario 2: Demonstrate a full login attempt flow (success and failure) ---
        System.out.println("\n--- SCENARIO 2: Demonstrating a complete login attempt flow (including success and failure) ---");

        // Display initial login form
        loginController.showLoginForm();

        // Simulate a failed login attempt
        System.out.println("\nAttempting login with invalid credentials...");
        loginController.processLoginAttempt("wronguser", "badpass");

        // Simulate a successful login attempt
        System.out.println("\nAttempting login with valid credentials...");
        loginController.processLoginAttempt("admin", "password");

        System.out.println("--- End of SCENARIO 2 ---");
        System.out.println("\n--- Login Application Demonstration Finished ---");
    }
}
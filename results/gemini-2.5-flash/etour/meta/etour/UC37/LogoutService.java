package com.example.logoutapp;

import java.util.Scanner;

/**
 * LogoutService class handles the process of logging out a user.
 * It interacts with the UserSession to update the login status
 * and prompts the user for confirmation.
 */
public class LogoutService {
    private UserSession userSession;
    private Scanner scanner;

    /**
     * Constructor for LogoutService.
     *
     * @param userSession The active user session to manage.
     * @param scanner     A Scanner object for user input.
     */
    public LogoutService(UserSession userSession, Scanner scanner) {
        this.userSession = userSession;
        this.scanner = scanner;
    }

    /**
     * Initiates the logout process.
     * This method checks if a user is logged in, asks for confirmation,
     * and then performs the logout if confirmed.
     *
     * @return true if the logout was successful, false otherwise.
     */
    public boolean initiateLogout() {
        // Entry condition check: A registered user has previously made a successful Login.
        if (!userSession.isLoggedIn()) {
            System.out.println("No user is currently logged in. Cannot perform logout.");
            return false;
        }

        System.out.println("\n--- Logout Process ---");
        System.out.println("You are currently logged in as: " + userSession.getUsername());

        // Step 1: Access the functionality of disconnection from the system.
        // (This is implicitly done by calling initiateLogout method)

        // Step 2: Asks for confirmation of the transaction.
        System.out.print("Are you sure you want to log out? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        // Step 3: Confirm the request for disconnection.
        if (confirmation.equals("yes")) {
            // Step 4: Disconnects the Registered User.
            userSession.logout();
            // Exit condition: The system shall notify the successful operation logout.
            System.out.println("Logout successful. Goodbye, " + userSession.getUsername() + "!");
            return true;
        } else {
            System.out.println("Logout cancelled. You are still logged in.");
            return false;
        }
    }
}
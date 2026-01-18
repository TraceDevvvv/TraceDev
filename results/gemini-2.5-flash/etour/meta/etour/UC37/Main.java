package com.example.logoutapp;

import java.util.Scanner;

/**
 * Main class to demonstrate the Logout use case.
 * This class simulates a user logging in and then attempting to log out,
 * showcasing the interaction between UserSession and LogoutService.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize UserSession and Scanner for user input
        UserSession userSession = new UserSession();
        Scanner scanner = new Scanner(System.in);
        LogoutService logoutService = new LogoutService(userSession, scanner);

        System.out.println("--- Simulating User Login and Logout ---");

        // Scenario 1: Attempt to logout when no user is logged in (edge case)
        System.out.println("\nAttempting to logout without being logged in:");
        logoutService.initiateLogout();
        System.out.println("Current login status: " + (userSession.isLoggedIn() ? "Logged In" : "Logged Out"));

        // Simulate a successful login
        System.out.println("\nSimulating user login...");
        userSession.login("testUser");
        System.out.println("Current login status: " + (userSession.isLoggedIn() ? "Logged In as " + userSession.getUsername() : "Logged Out"));

        // Scenario 2: User is logged in and confirms logout
        System.out.println("\nAttempting to logout with user logged in (confirm 'yes'):");
        // The initiateLogout method will handle prompting for confirmation
        logoutService.initiateLogout();
        System.out.println("Current login status: " + (userSession.isLoggedIn() ? "Logged In as " + userSession.getUsername() : "Logged Out"));

        // Simulate another successful login for the next scenario
        System.out.println("\nSimulating user login again...");
        userSession.login("anotherUser");
        System.out.println("Current login status: " + (userSession.isLoggedIn() ? "Logged In as " + userSession.getUsername() : "Logged Out"));

        // Scenario 3: User is logged in but cancels logout
        System.out.println("\nAttempting to logout with user logged in (confirm 'no'):");
        // The initiateLogout method will handle prompting for confirmation
        logoutService.initiateLogout();
        System.out.println("Current login status: " + (userSession.isLoggedIn() ? "Logged In as " + userSession.getUsername() : "Logged Out"));

        // Close the scanner
        scanner.close();
        System.out.println("\n--- Simulation End ---");
    }
}
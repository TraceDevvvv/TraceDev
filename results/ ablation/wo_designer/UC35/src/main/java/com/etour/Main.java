package com.etour;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create authentication service and scanner for user input
        AuthenticationService authService = new AuthenticationService();
        Scanner scanner = new Scanner(System.in);
        
        // Display login form
        System.out.println("=== ETOUR Login System ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        try {
            // Attempt to authenticate user
            User user = authService.authenticate(username, password);
            System.out.println("Login successful! Welcome, " + user.getUsername());
            // Display work area after successful login
            displayWorkArea(user);
        } catch (AuthenticationException e) {
            System.out.println("Login error: " + e.getMessage());
        } catch (ServerConnectionException e) {
            System.out.println("System error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    private static void displayWorkArea(User user) {
        System.out.println("\n=== Work Area ===");
        System.out.println("User: " + user.getUsername());
        System.out.println("Role: " + user.getRole());
        System.out.println("Access level: " + user.getAccessLevel());
        System.out.println("\nAvailable functions:");
        System.out.println("- View bookings");
        System.out.println("- Make reservations");
        System.out.println("- Update profile");
        System.out.println("- View travel history");
    }
}
package com.restaurant.dailymenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DailyMenuService service = new DailyMenuService();
        
        System.out.println("Welcome, Point Of Restaurant Operator!");
        System.out.println("Daily Menu Deletion System");
        System.out.println("==========================");
        
        // Simulate authentication (in real scenario this would be separate)
        System.out.print("Enter authentication token: ");
        String token = scanner.nextLine();
        
        if (!service.authenticate(token)) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }
        
        System.out.println("Authentication successful.");
        
        // Step 1: Enable delete functionality
        service.enableDeleteFunctionality();
        
        // Step 2-4: Display form and get day selection
        DayOfWeek selectedDay = service.displayFormAndGetSelection(scanner);
        if (selectedDay == null) {
            System.out.println("Operation cancelled.");
            return;
        }
        
        // Step 5-6: Confirm deletion
        boolean confirmed = service.confirmDeletion(scanner, selectedDay);
        if (!confirmed) {
            System.out.println("Operation cancelled by user.");
            return;
        }
        
        try {
            // Step 7: Delete the daily menu
            boolean success = service.deleteDailyMenu(selectedDay);
            
            if (success) {
                System.out.println("Success: Daily menu for " + selectedDay + " has been deleted.");
            } else {
                System.out.println("Error: Failed to delete daily menu for " + selectedDay);
            }
        } catch (ServerConnectionException e) {
            System.out.println("Error: Connection to server interrupted. " + e.getMessage());
        }
        
        scanner.close();
    }
}
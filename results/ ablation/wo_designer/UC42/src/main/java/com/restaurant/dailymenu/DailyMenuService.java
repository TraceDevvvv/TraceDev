
package com.restaurant.dailymenu;

import java.util.Scanner;

public class DailyMenuService {
    // Simulated database or service layer
    private MenuDatabase database;
    private boolean deleteEnabled;
    
    public DailyMenuService() {
        this.database = new MenuDatabase();
        this.deleteEnabled = false;
    }
    
    /**
     * Authenticate the operator
     * @param token authentication token
     * @return true if authentication successful
     */
    public boolean authenticate(String token) {
        // In real implementation, this would validate against an auth service
        return token != null && !token.trim().isEmpty();
    }
    
    /**
     * Enable delete functionality (Step 1)
     */
    public void enableDeleteFunctionality() {
        this.deleteEnabled = true;
        System.out.println("Delete functionality enabled.");
    }
    
    /**
     * Display form with days of week and get user selection (Steps 2-4)
     * @param scanner input scanner
     * @return selected DayOfWeek or null if cancelled
     */
    public DayOfWeek displayFormAndGetSelection(Scanner scanner) {
        if (!deleteEnabled) {
            System.out.println("Error: Delete functionality not enabled.");
            return null;
        }
        
        System.out.println("\nSelect a day to delete its daily menu:");
        System.out.println("1. Monday");
        System.out.println("2. Tuesday");
        System.out.println("3. Wednesday");
        System.out.println("4. Thursday");
        System.out.println("5. Friday");
        System.out.println("6. Saturday");
        System.out.println("7. Sunday");
        System.out.println("0. Cancel");
        
        System.out.print("Enter choice (0-7): ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1: return DayOfWeek.MONDAY;
                case 2: return DayOfWeek.TUESDAY;
                case 3: return DayOfWeek.WEDNESDAY;
                case 4: return DayOfWeek.THURSDAY;
                case 5: return DayOfWeek.FRIDAY;
                case 6: return DayOfWeek.SATURDAY;
                case 7: return DayOfWeek.SUNDAY;
                case 0: return null;
                default: 
                    System.out.println("Invalid choice. Please enter 0-7.");
                    return displayFormAndGetSelection(scanner); // Retry
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number 0-7.");
            return displayFormAndGetSelection(scanner); // Retry
        }
    }
    
    /**
     * Confirm the deletion with user (Steps 5-6)
     * @param scanner input scanner
     * @param day day to delete
     * @return true if user confirms
     */
    public boolean confirmDeletion(Scanner scanner, DayOfWeek day) {
        System.out.println("\nConfirm deletion of daily menu for " + day + "?");
        System.out.println("This action cannot be undone.");
        System.out.print("Type 'CONFIRM' to delete, or anything else to cancel: ");
        
        String response = scanner.nextLine();
        return "CONFIRM".equalsIgnoreCase(response);
    }
    
    /**
     * Delete the daily menu for selected day (Step 7)
     * @param day day to delete menu for
     * @return true if deletion successful
     * @throws ServerConnectionException if connection to server fails
     */
    public boolean deleteDailyMenu(DayOfWeek day) throws ServerConnectionException {
        if (!deleteEnabled) {
            System.out.println("Error: Delete functionality not enabled.");
            return false;
        }
        
        if (day == null) {
            System.out.println("Error: No day selected.");
            return false;
        }
        
        // Simulate server connection check
        if (!checkServerConnection()) {
            throw new ServerConnectionException("Unable to connect to ETOUR server.");
        }
        
        // Perform deletion
        return database.deleteMenu(day);
    }
    
    /**
     * Check connection to server
     * @return true if connection is available
     */
    private boolean checkServerConnection() {
        // Simulated connection check
        // In real implementation, this would ping the server
        return Math.random() > 0.1; // 90% chance of success for simulation
    }
}

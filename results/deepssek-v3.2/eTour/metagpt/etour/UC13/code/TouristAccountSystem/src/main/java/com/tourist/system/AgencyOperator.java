package com.tourist.system;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * AgencyOperator class handles the user interaction flow for 
 * activating/deactivating tourist accounts.
 * 
 * This class follows the use case flow:
 * 1. Activate the feature for activation/deactivation of a given tourist
 * 2. Ask for confirmation
 * 3. Confirm the operation
 * 4. Enable/disable the selected tourist account
 * 
 * Exit conditions: Notification of outcome
 * Handles edge cases like server connection interruption (ETOUR)
 */
public class AgencyOperator {
    private TouristAccountService accountService;
    private Scanner scanner;
    
    /**
     * Constructor initializes the service and scanner
     */
    public AgencyOperator() {
        this.accountService = new TouristAccountService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main interactive method that runs the activation/deactivation feature
     */
    public void runActivationFeature() {
        System.out.println("==========================================");
        System.out.println("   TOURIST ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("\nWelcome Agency Operator!");
        
        try {
            // Check server connection before proceeding
            System.out.println("Checking connection to ETOUR server...");
            accountService.simulateServerConnection();
            System.out.println("✓ Connection to ETOUR server established.");
        } catch (TouristAccountService.ServerConnectionException e) {
            System.out.println("✗ Error: " + e.getMessage());
            System.out.println("Please try again later or contact system administrator.");
            return;
        }
        
        boolean continueOperating = true;
        
        while (continueOperating) {
            displayMainMenu();
            
            try {
                int choice = getUserChoice(1, 4);
                
                switch (choice) {
                    case 1:
                        showAllTourists();
                        break;
                    case 2:
                        activateDeactivateTouristAccount();
                        break;
                    case 3:
                        checkTouristStatus();
                        break;
                    case 4:
                        System.out.println("\nThank you for using the Tourist Account Management System. Goodbye!");
                        continueOperating = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            } catch (Exception e) {
                System.out.println("\nAn unexpected error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear any remaining input
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu options
     */
    private void displayMainMenu() {
        System.out.println("\n------------------------------------------");
        System.out.println("MAIN MENU");
        System.out.println("------------------------------------------");
        System.out.println("1. View All Tourists");
        System.out.println("2. Activate/Deactivate Tourist Account");
        System.out.println("3. Check Tourist Account Status");
        System.out.println("4. Exit");
        System.out.print("\nPlease enter your choice (1-4): ");
    }
    
    /**
     * Gets user choice within a specified range
     * @param min Minimum valid choice
     * @param max Maximum valid choice
     * @return Validated user choice
     */
    private int getUserChoice(int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                }
            } catch (InputMismatchException e) {
                System.out.printf("Invalid input. Please enter a number between %d and %d: ", min, max);
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Displays all tourists in the system
     */
    private void showAllTourists() {
        System.out.println("\n------------------------------------------");
        System.out.println("ALL TOURISTS IN SYSTEM");
        System.out.println("------------------------------------------");
        
        try {
            List<Tourist> tourists = accountService.getAllTourists();
            
            if (tourists.isEmpty()) {
                System.out.println("No tourists found in the system.");
                return;
            }
            
            System.out.printf("%-8s %-20s %-30s %-10s%n", 
                "ID", "Name", "Email", "Status");
            System.out.println("------------------------------------------------------------------------");
            
            for (Tourist tourist : tourists) {
                System.out.printf("%-8s %-20s %-30s %-10s%n",
                    tourist.getId(),
                    tourist.getName(),
                    tourist.getEmail(),
                    tourist.getStatusDescription());
            }
            
            System.out.println("\nTotal tourists: " + tourists.size());
        } catch (TouristAccountService.ServerConnectionException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
            System.out.println("Cannot retrieve tourist data due to server connection issues.");
        }
    }
    
    /**
     * Implements the main use case: Activate/Deactivate tourist account
     * with confirmation steps as per the requirements
     */
    private void activateDeactivateTouristAccount() {
        System.out.println("\n------------------------------------------");
        System.out.println("ACTIVATE/DEACTIVATE TOURIST ACCOUNT");
        System.out.println("------------------------------------------");
        
        // Step 1: Get tourist ID
        System.out.print("Enter Tourist ID to modify (or type 'back' to return): ");
        String touristId = scanner.nextLine().trim();
        
        if (touristId.equalsIgnoreCase("back")) {
            System.out.println("Returning to main menu...");
            return;
        }
        
        Tourist tourist = null;
        
        try {
            // Step 1.1: Retrieve tourist information
            tourist = accountService.findTouristById(touristId);
            
            if (tourist == null) {
                System.out.println("\n✗ Tourist with ID '" + touristId + "' not found.");
                return;
            }
            
            // Display current tourist information
            System.out.println("\nTourist Information:");
            System.out.println("  ID: " + tourist.getId());
            System.out.println("  Name: " + tourist.getName());
            System.out.println("  Email: " + tourist.getEmail());
            System.out.println("  Current Status: " + tourist.getStatusDescription());
            
            // Step 2: Ask for confirmation
            System.out.println("\n------------------------------------------");
            String action = tourist.isActive() ? "DEACTIVATE" : "ACTIVATE";
            System.out.println("CONFIRMATION REQUIRED");
            System.out.println("------------------------------------------");
            System.out.println("You are about to " + action + " this tourist's account.");
            System.out.println("This action will " + (tourist.isActive() ? "disable" : "enable") + " their access.");
            
            // Step 3: Confirm the operation
            System.out.print("\nAre you sure you want to proceed? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("yes") && !confirmation.equals("y")) {
                System.out.println("\nOperation cancelled. Returning to main menu.");
                return;
            }
            
            // Step 4: Enable/disable the selected tourist account
            System.out.println("\nPerforming " + action + " operation...");
            
            boolean success;
            String newStatus;
            
            if (tourist.isActive()) {
                success = accountService.deactivateTouristAccount(touristId);
                newStatus = "Inactive";
            } else {
                success = accountService.activateTouristAccount(touristId);
                newStatus = "Active";
            }
            
            // Exit condition: Notification of outcome
            if (success) {
                System.out.println("\n✓ SUCCESS: Tourist account has been " + 
                    (tourist.isActive() ? "deactivated" : "activated") + ".");
                System.out.println("  New Status: " + newStatus);
                System.out.println("  Tourist ID: " + touristId);
                System.out.println("  Name: " + tourist.getName());
                
                // Verify the change
                Tourist updatedTourist = accountService.findTouristById(touristId);
                if (updatedTourist != null) {
                    System.out.println("  Verified Status: " + updatedTourist.getStatusDescription());
                }
            } else {
                System.out.println("\n✗ FAILED: Could not update tourist account.");
                System.out.println("  Please verify the tourist ID and try again.");
            }
            
        } catch (TouristAccountService.ServerConnectionException e) {
            System.out.println("\n✗ ERROR: Operation failed due to server connection issue.");
            System.out.println("  Details: " + e.getMessage());
            System.out.println("  Please try again later or contact system administrator.");
        } catch (IllegalArgumentException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        }
    }
    
    /**
     * Checks the status of a specific tourist account
     */
    private void checkTouristStatus() {
        System.out.println("\n------------------------------------------");
        System.out.println("CHECK TOURIST ACCOUNT STATUS");
        System.out.println("------------------------------------------");
        
        System.out.print("Enter Tourist ID to check (or type 'back' to return): ");
        String touristId = scanner.nextLine().trim();
        
        if (touristId.equalsIgnoreCase("back")) {
            System.out.println("Returning to main menu...");
            return;
        }
        
        try {
            Tourist tourist = accountService.findTouristById(touristId);
            
            if (tourist == null) {
                System.out.println("\n✗ Tourist with ID '" + touristId + "' not found.");
                return;
            }
            
            System.out.println("\n✓ Tourist Account Status:");
            System.out.println("  ID: " + tourist.getId());
            System.out.println("  Name: " + tourist.getName());
            System.out.println("  Email: " + tourist.getEmail());
            System.out.println("  Status: " + tourist.getStatusDescription());
            System.out.println("  Active: " + tourist.isActive());
            
        } catch (TouristAccountService.ServerConnectionException e) {
            System.out.println("\n✗ Error: " + e.getMessage());
            System.out.println("Cannot retrieve tourist status due to server connection issues.");
        }
    }
    
    /**
     * Method to demonstrate the complete use case flow programmatically
     * This can be used for testing without user interaction
     * @param touristId The tourist ID to process
     * @param confirm Whether to confirm the operation
     * @return Result message of the operation
     */
    public String processTouristAccountChange(String touristId, boolean confirm) {
        try {
            Tourist tourist = accountService.findTouristById(touristId);
            
            if (tourist == null) {
                return "FAILED: Tourist with ID '" + touristId + "' not found.";
            }
            
            String currentStatus = tourist.getStatusDescription();
            String action = tourist.isActive() ? "deactivate" : "activate";
            
            if (!confirm) {
                return "CANCELLED: Operation not confirmed for tourist ID '" + touristId + "'.";
            }
            
            boolean success;
            if (tourist.isActive()) {
                success = accountService.deactivateTouristAccount(touristId);
            } else {
                success = accountService.activateTouristAccount(touristId);
            }
            
            if (success) {
                Tourist updatedTourist = accountService.findTouristById(touristId);
                String newStatus = updatedTourist != null ? updatedTourist.getStatusDescription() : "Unknown";
                return String.format("SUCCESS: Tourist '%s' (%s) account changed from %s to %s.", 
                    tourist.getName(), touristId, currentStatus, newStatus);
            } else {
                return "FAILED: Could not update tourist account for ID '" + touristId + "'.";
            }
            
        } catch (TouristAccountService.ServerConnectionException e) {
            return "ERROR: Server connection failed - " + e.getMessage();
        }
    }
}
package com.etour.culturalheritage;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Controller class for managing the UI flow of deleting cultural heritage objects.
 * Handles viewing lists, selection, confirmation dialogs, deletion execution,
 * and input control blocking to prevent multiple submissions.
 * Implements the complete DeleteCulturalHeritage use case flow.
 */
public class CulturalHeritageController {
    private final CulturalHeritageService culturalHeritageService;
    private final Scanner scanner;
    private boolean isLoggedIn;
    
    /**
     * Constructor initializes the controller with service and scanner.
     */
    public CulturalHeritageController() {
        this.culturalHeritageService = new CulturalHeritageService();
        this.scanner = new Scanner(System.in);
        this.isLoggedIn = false;
    }
    
    /**
     * Main entry point for the DeleteCulturalHeritage use case.
     * This method orchestrates the complete flow from login to deletion.
     */
    public void executeDeleteCulturalHeritageUseCase() {
        System.out.println("=== Delete Cultural Heritage Use Case ===");
        
        // Entry condition: Agency must be logged in
        if (!loginAgencyOperator()) {
            System.out.println("Login failed. Exiting use case.");
            return;
        }
        
        boolean continueOperations = true;
        
        while (continueOperations && isLoggedIn) {
            // Step 1: View list of CulturalHeritage from SearchCulturalHeritage use case
            List<CulturalHeritage> culturalHeritageList = viewCulturalHeritageList();
            
            if (culturalHeritageList.isEmpty()) {
                System.out.println("No cultural heritage objects available for deletion.");
                System.out.println("Would you like to exit? (yes/no)");
                String exitChoice = scanner.nextLine().trim().toLowerCase();
                if (exitChoice.equals("yes") || exitChoice.equals("y")) {
                    continueOperations = false;
                    continue;
                } else {
                    continue;
                }
            }
            
            // Select a cultural heritage for deletion
            CulturalHeritage selectedHeritage = selectCulturalHeritage(culturalHeritageList);
            
            if (selectedHeritage == null) {
                // User chose to cancel or go back
                continue;
            }
            
            // Step 2: Ask confirmation
            boolean confirmed = askConfirmation(selectedHeritage);
            
            if (!confirmed) {
                System.out.println("Deletion cancelled by operator.");
                continue;
            }
            
            // Generate a unique confirmation token to track this specific deletion request
            String confirmationToken = generateConfirmationToken();
            
            // Block input controls to prevent multiple submissions
            blockInputControls(selectedHeritage.getId());
            
            try {
                // Step 3: Confirm operation (validate token)
                if (!culturalHeritageService.confirmDeletion(selectedHeritage.getId(), confirmationToken)) {
                    System.out.println("Confirmation validation failed. Operation cancelled.");
                    continue;
                }
                
                // Step 4: Delete the selected cultural object
                CulturalHeritageService.DeletionResult result = 
                    culturalHeritageService.deleteCulturalHeritage(selectedHeritage.getId(), confirmationToken);
                
                // Exit condition: System notifies successful deletion
                if (result.isSuccess()) {
                    System.out.println("\n✅ " + result.getMessage());
                    System.out.println("The cultural heritage has been successfully deleted from the system.");
                } else {
                    System.out.println("\n❌ " + result.getMessage());
                    
                    // Check if failure was due to server connection interruption
                    if (result.getMessage().contains("Connection to ETOUR server interrupted")) {
                        System.out.println("Exit condition: Server ETOUR connection interrupted.");
                        handleServerDisconnection();
                    }
                }
                
            } finally {
                // Always unblock input controls after operation completes
                unblockInputControls(selectedHeritage.getId());
            }
            
            // Ask if user wants to delete another cultural heritage
            System.out.println("\nWould you like to delete another cultural heritage? (yes/no)");
            String continueChoice = scanner.nextLine().trim().toLowerCase();
            if (continueChoice.equals("no") || continueChoice.equals("n")) {
                continueOperations = false;
            }
        }
        
        logout();
        System.out.println("=== Delete Cultural Heritage Use Case Completed ===");
    }
    
    /**
     * Simulates agency operator login (entry condition).
     * In a real system, this would involve authentication against a user database.
     * 
     * @return true if login successful, false otherwise
     */
    private boolean loginAgencyOperator() {
        System.out.println("\n--- Agency Operator Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        
        // Simple authentication simulation
        // In production, this would validate against a secure authentication system
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Login failed: Username and password cannot be empty.");
            return false;
        }
        
        // Simulate authentication delay
        simulateAuthenticationDelay();
        
        // For demo purposes, accept any non-empty credentials
        isLoggedIn = true;
        System.out.println("Login successful! Welcome, Agency Operator.");
        return true;
    }
    
    /**
     * Logs out the agency operator.
     */
    private void logout() {
        if (isLoggedIn) {
            System.out.println("\nLogging out Agency Operator...");
            isLoggedIn = false;
            System.out.println("Logout successful.");
        }
    }
    
    /**
     * Step 1: View list of CulturalHeritage as a result of SearchCulturalHeritage use case.
     * Displays all available cultural heritage objects with their details.
     * 
     * @return List of cultural heritage objects
     */
    private List<CulturalHeritage> viewCulturalHeritageList() {
        System.out.println("\n--- Cultural Heritage List ---");
        
        try {
            List<CulturalHeritage> heritageList = culturalHeritageService.getAllCulturalHeritage();
            
            if (heritageList.isEmpty()) {
                System.out.println("No cultural heritage objects found.");
                return heritageList;
            }
            
            System.out.println("Total cultural heritage objects: " + heritageList.size());
            System.out.println("----------------------------------------");
            
            for (int i = 0; i < heritageList.size(); i++) {
                CulturalHeritage heritage = heritageList.get(i);
                System.out.printf("%d. ID: %s | Name: %s | Location: %s%n",
                    i + 1,
                    heritage.getId(),
                    heritage.getName(),
                    heritage.getLocation());
                System.out.println("   Description: " + 
                    (heritage.getDescription().length() > 50 ? 
                     heritage.getDescription().substring(0, 50) + "..." : 
                     heritage.getDescription()));
                System.out.println("   Created: " + heritage.getCreationDate());
                System.out.println("----------------------------------------");
            }
            
            return heritageList;
            
        } catch (IllegalStateException e) {
            System.out.println("Error retrieving cultural heritage list: " + e.getMessage());
            System.out.println("Exit condition: Server ETOUR connection interrupted.");
            handleServerDisconnection();
            return List.of();
        }
    }
    
    /**
     * Allows the user to select a cultural heritage from the list for deletion.
     * 
     * @param heritageList The list of available cultural heritage objects
     * @return The selected CulturalHeritage object, or null if user cancels
     */
    private CulturalHeritage selectCulturalHeritage(List<CulturalHeritage> heritageList) {
        if (heritageList.isEmpty()) {
            return null;
        }
        
        while (true) {
            System.out.println("\nEnter the number of the cultural heritage to delete (1-" + 
                heritageList.size() + "), or '0' to cancel: ");
            
            String input = scanner.nextLine().trim();
            
            if (input.equals("0")) {
                System.out.println("Selection cancelled by operator.");
                return null;
            }
            
            try {
                int choice = Integer.parseInt(input);
                
                if (choice >= 1 && choice <= heritageList.size()) {
                    CulturalHeritage selected = heritageList.get(choice - 1);
                    System.out.println("Selected: " + selected.getName() + " (ID: " + selected.getId() + ")");
                    return selected;
                } else {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + 
                        heritageList.size() + ", or 0 to cancel.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    /**
     * Step 2: Ask confirmation for deletion.
     * Presents the details of the selected cultural heritage and asks for confirmation.
     * 
     * @param heritage The cultural heritage to be deleted
     * @return true if user confirms deletion, false if user cancels
     */
    private boolean askConfirmation(CulturalHeritage heritage) {
        System.out.println("\n--- CONFIRM DELETION ---");
        System.out.println("You are about to delete the following cultural heritage:");
        System.out.println("ID: " + heritage.getId());
        System.out.println("Name: " + heritage.getName());
        System.out.println("Description: " + heritage.getDescription());
        System.out.println("Location: " + heritage.getLocation());
        System.out.println("Creation Date: " + heritage.getCreationDate());
        System.out.println("\n⚠️  WARNING: This action cannot be undone!");
        System.out.println("All data associated with this cultural heritage will be permanently deleted.");
        
        while (true) {
            System.out.print("\nDo you want to proceed with deletion? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (confirmation.equals("yes") || confirmation.equals("y")) {
                return true;
            } else if (confirmation.equals("no") || confirmation.equals("n")) {
                return false;
            } else {
                System.out.println("Please enter 'yes' to confirm or 'no' to cancel.");
            }
        }
    }
    
    /**
     * Generates a unique confirmation token for tracking the deletion request.
     * In a real system, this would be a secure token tied to the user's session.
     * 
     * @return A unique confirmation token
     */
    private String generateConfirmationToken() {
        return "CONF-" + UUID.randomUUID().toString().substring(0, 8) + "-" + System.currentTimeMillis();
    }
    
    /**
     * Blocks input controls to prevent multiple submissions before operation completion.
     * This simulates disabling UI buttons or input fields in a graphical interface.
     * 
     * @param heritageId The ID of the cultural heritage being deleted
     */
    private void blockInputControls(String heritageId) {
        System.out.println("\n[System] Blocking input controls to prevent multiple submissions...");
        System.out.println("[System] Delete button disabled. Please wait for operation to complete.");
        
        // In a real GUI application, this would disable buttons/inputs
        // For console application, we simulate by showing status
        if (culturalHeritageService.isDeletionInProgress(heritageId)) {
            System.out.println("[System] Deletion operation is already in progress. Please wait.");
        }
    }
    
    /**
     * Unblocks input controls after operation completion.
     * 
     * @param heritageId The ID of the cultural heritage
     */
    private void unblockInputControls(String heritageId) {
        System.out.println("[System] Input controls unblocked. Delete functionality restored.");
        
        // Small delay to simulate UI update
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Handles server disconnection scenario.
     * Provides options to retry or exit.
     */
    private void handleServerDisconnection() {
        System.out.println("\n--- Server Connection Interrupted ---");
        System.out.println("The connection to the ETOUR server has been lost.");
        
        while (true) {
            System.out.println("Options:");
            System.out.println("1. Try to reconnect");
            System.out.println("2. Exit use case");
            System.out.print("Enter your choice (1-2): ");
            
            String choice = scanner.nextLine().trim();
            
            if (choice.equals("1")) {
                System.out.println("Attempting to reconnect to server...");
                
                // Simulate reconnection attempt
                try {
                    Thread.sleep(1500);
                    
                    // Randomly determine if reconnection succeeds
                    if (Math.random() > 0.3) { // 70% success rate
                        culturalHeritageService.setServerConnected(true);
                        System.out.println("Reconnection successful!");
                        return;
                    } else {
                        System.out.println("Reconnection failed. Please try again.");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Reconnection interrupted.");
                    break;
                }
            } else if (choice.equals("2")) {
                System.out.println("Exiting due to server connection issues.");
                isLoggedIn = false;
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }
    
    /**
     * Simulates authentication delay.
     */
    private void simulateAuthenticationDelay() {
        try {
            System.out.print("Authenticating");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(300);
                System.out.print(".");
            }
            Thread.sleep(300);
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Gets the current cultural heritage count for testing/debugging.
     * 
     * @return The number of cultural heritage objects in the system
     */
    public int getCulturalHeritageCount() {
        return culturalHeritageService.getCulturalHeritageCount();
    }
    
    /**
     * Checks if the agency operator is currently logged in.
     * 
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * For testing purposes: Manually set login status.
     * 
     * @param loggedIn The login status to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }
    
    /**
     * Gets the cultural heritage service instance.
     * 
     * @return The CulturalHeritageService instance
     */
    public CulturalHeritageService getCulturalHeritageService() {
        return culturalHeritageService;
    }
}
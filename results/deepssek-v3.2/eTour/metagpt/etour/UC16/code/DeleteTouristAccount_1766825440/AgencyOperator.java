package com.etour.operator;

import com.etour.tourist.Tourist;
import com.etour.service.TouristService;
import java.util.List;
import java.util.Scanner;

/**
 * AgencyOperator class represents the agency operator actor in the system.
 * This class handles the operator's authentication and the main workflow
 * for deleting tourist accounts as specified in the DeleteTouristAccount use case.
 */
public class AgencyOperator {
    private String operatorId;      // Unique identifier for the operator
    private String operatorName;    // Full name of the operator
    private boolean isLoggedIn;     // Login status of the operator
    private TouristService touristService; // Service for tourist operations
    
    /**
     * Constructor initializes the operator with an ID and name.
     * Also creates an instance of TouristService for tourist operations.
     * 
     * @param operatorId the unique identifier for the operator
     * @param operatorName the full name of the operator
     */
    public AgencyOperator(String operatorId, String operatorName) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.isLoggedIn = false; // Operator starts as logged out
        this.touristService = new TouristService();
    }
    
    /**
     * Gets the operator's ID.
     * 
     * @return the operator ID
     */
    public String getOperatorId() {
        return operatorId;
    }
    
    /**
     * Gets the operator's name.
     * 
     * @return the operator name
     */
    public String getOperatorName() {
        return operatorName;
    }
    
    /**
     * Checks if the operator is currently logged in.
     * Required for the entry condition: "The agency has logged"
     * 
     * @return true if operator is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * Simulates operator login with credentials.
     * In a real application, this would connect to an authentication service.
     * 
     * @param username the operator's username
     * @param password the operator's password
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        // Simplified authentication for demonstration
        // In a real application, this would validate against a database or LDAP
        
        // Edge case: Empty credentials
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            System.out.println("Error: Username and password cannot be empty.");
            return false;
        }
        
        // For demonstration, accept any non-empty credentials
        this.isLoggedIn = true;
        System.out.println("Success: Operator " + operatorName + " (ID: " + operatorId + 
                          ") has logged in successfully.");
        return true;
    }
    
    /**
     * Logs out the operator and updates the login status.
     */
    public void logout() {
        if (isLoggedIn) {
            this.isLoggedIn = false;
            System.out.println("Success: Operator " + operatorName + " has logged out.");
        } else {
            System.out.println("Info: Operator is already logged out.");
        }
    }
    
    /**
     * Main method that implements the DeleteTouristAccount use case workflow.
     * Follows the exact flow specified in the use case:
     * 1. Search for tourists (activates SearchTourist use case)
     * 2. Select a tourist from the list
     * 3. Ask for confirmation
     * 4. Confirm and delete the account
     * 
     * This method handles all edge cases and provides appropriate feedback.
     */
    public void deleteTouristAccount() {
        // Entry condition check: operator must be logged in
        if (!isLoggedIn) {
            System.out.println("Error: Agency operator must be logged in to delete tourist accounts.");
            System.out.println("Please login first.");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== DELETE TOURIST ACCOUNT WORKFLOW ===");
        System.out.println("Operator: " + operatorName + " (ID: " + operatorId + ")");
        System.out.println("========================================\n");
        
        // Step 1: Search for tourists
        System.out.println("Step 1: Search for Tourists");
        System.out.println("-----------------------------");
        System.out.print("Enter search term (name, email, ID) or press Enter to see all tourists: ");
        String searchQuery = scanner.nextLine();
        
        List<Tourist> searchResults = touristService.searchTourist(searchQuery);
        
        // Edge case: No tourists found
        if (searchResults == null || searchResults.isEmpty()) {
            System.out.println("No tourists found matching your search criteria.");
            System.out.println("Operation cancelled.");
            return;
        }
        
        // Display search results
        touristService.displayTourists(searchResults);
        
        // Step 2: Select a tourist from the list
        System.out.println("Step 2: Select Tourist for Deletion");
        System.out.println("-------------------------------------");
        System.out.print("Enter the ID of the tourist you want to delete: ");
        String selectedTouristId = scanner.nextLine().trim();
        
        // Validate the selected tourist ID exists in the search results
        boolean validSelection = false;
        for (Tourist tourist : searchResults) {
            if (tourist.getId().equals(selectedTouristId)) {
                validSelection = true;
                break;
            }
        }
        
        if (!validSelection) {
            System.out.println("Error: Invalid selection. Tourist ID not found in the search results.");
            System.out.println("Operation cancelled.");
            return;
        }
        
        // Step 3: Ask for confirmation
        System.out.println("\nStep 3: Confirmation");
        System.out.println("----------------------");
        
        // Edge case: Check server connection before proceeding
        if (!touristService.isServerConnected()) {
            System.out.println("Error: Cannot proceed with deletion. Server connection to ETOUR is interrupted.");
            System.out.println("Please ensure server connectivity and try again.");
            return;
        }
        
        boolean confirmed = touristService.confirmDeletion(selectedTouristId);
        
        if (!confirmed) {
            System.out.println("Deletion cancelled by operator.");
            return;
        }
        
        // Step 4: Delete the selected tourist account
        System.out.println("\nStep 4: Delete Tourist Account");
        System.out.println("-------------------------------");
        
        boolean deletionSuccessful = touristService.deleteTouristAccount(selectedTouristId);
        
        if (deletionSuccessful) {
            System.out.println("\n=== OPERATION COMPLETED SUCCESSFULLY ===");
            System.out.println("Tourist account deletion workflow completed.");
            
            // Exit condition: Notification system has eliminated the selected account
            System.out.println("Exit condition satisfied: The system has eliminated the selected tourist account.");
        } else {
            System.out.println("\n=== OPERATION FAILED ===");
            System.out.println("Failed to delete tourist account. See error messages above.");
        }
    }
    
    /**
     * Simulates server connection interruption for testing edge cases.
     * This allows testing of the "Interruption of the connection to the server ETOUR" scenario.
     */
    public void simulateServerConnectionInterruption() {
        touristService.simulateServerDisconnection();
        System.out.println("Server connection interruption simulated.");
    }
    
    /**
     * Simulates server reconnection for testing.
     */
    public void simulateServerReconnection() {
        touristService.simulateServerReconnection();
        System.out.println("Server reconnection simulated.");
    }
    
    /**
     * Displays the current status of the operator and tourist service.
     * Useful for debugging and status checks.
     */
    public void displayStatus() {
        System.out.println("\n=== OPERATOR STATUS ===");
        System.out.println("Operator ID: " + operatorId);
        System.out.println("Operator Name: " + operatorName);
        System.out.println("Login Status: " + (isLoggedIn ? "LOGGED IN" : "LOGGED OUT"));
        System.out.println("Server Connection: " + 
                          (touristService.isServerConnected() ? "CONNECTED" : "DISCONNECTED"));
        System.out.println("Active Tourists: " + touristService.getActiveTouristCount());
        System.out.println("========================\n");
    }
}
package com.etour.service;

import com.etour.tourist.Tourist;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

/**
 * TouristService class handles tourist-related operations including searching,
 * deleting tourist accounts, and managing server connections.
 * This class follows the business logic for the DeleteTouristAccount use case.
 */
public class TouristService {
    
    private List<Tourist> tourists; // In-memory list of tourists (simulating database)
    private boolean serverConnected; // Simulates connection to ETOUR server
    
    /**
     * Constructor initializes the tourist list and sets up a sample dataset.
     * Also simulates server connection status.
     */
    public TouristService() {
        this.tourists = new ArrayList<>();
        this.serverConnected = true; // Assume server is initially connected
        
        // Initialize with sample data for demonstration
        initializeSampleTourists();
    }
    
    /**
     * Initializes the tourist list with sample data for testing.
     * In a real application, this would be replaced with database access.
     */
    private void initializeSampleTourists() {
        tourists.add(new Tourist("T001", "John Smith", "john.smith@example.com", "ACTIVE"));
        tourists.add(new Tourist("T002", "Emma Johnson", "emma.j@example.com", "ACTIVE"));
        tourists.add(new Tourist("T003", "David Wilson", "david.w@example.com", "ACTIVE"));
        tourists.add(new Tourist("T004", "Sarah Brown", "sarah.b@example.com", "INACTIVE"));
        tourists.add(new Tourist("T005", "Michael Lee", "michael.lee@example.com", "ACTIVE"));
    }
    
    /**
     * Checks if the server connection is available.
     * Simulates connection status for demonstration purposes.
     * 
     * @return true if server is connected, false otherwise
     */
    public boolean isServerConnected() {
        // In a real application, this would check actual server connectivity
        return serverConnected;
    }
    
    /**
     * Simulates server connection interruption.
     * For testing edge cases where server connection is lost.
     */
    public void simulateServerDisconnection() {
        this.serverConnected = false;
        System.out.println("Warning: Server connection to ETOUR has been interrupted.");
    }
    
    /**
     * Simulates server reconnection.
     */
    public void simulateServerReconnection() {
        this.serverConnected = true;
        System.out.println("Info: Server connection to ETOUR has been restored.");
    }
    
    /**
     * Searches for tourists based on a search query.
     * Implements the SearchTourist use case functionality.
     * In a real application, this would query a database with filters.
     * 
     * @param searchQuery the search term (name, email, or ID)
     * @return list of tourists matching the search criteria
     */
    public List<Tourist> searchTourist(String searchQuery) {
        List<Tourist> results = new ArrayList<>();
        
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            // If search query is empty, return all tourists
            results.addAll(tourists);
        } else {
            String query = searchQuery.toLowerCase().trim();
            
            // Search through all tourists for matches
            for (Tourist tourist : tourists) {
                if (tourist.getName().toLowerCase().contains(query) ||
                    tourist.getEmail().toLowerCase().contains(query) ||
                    tourist.getId().toLowerCase().contains(query)) {
                    results.add(tourist);
                }
            }
        }
        
        return results;
    }
    
    /**
     * Displays a list of tourists in a formatted way.
     * Used to present search results to the agency operator.
     * 
     * @param touristList the list of tourists to display
     */
    public void displayTourists(List<Tourist> touristList) {
        if (touristList == null || touristList.isEmpty()) {
            System.out.println("No tourists found.");
            return;
        }
        
        System.out.println("\n===== TOURIST LIST =====");
        System.out.println("ID\tName\t\tEmail\t\t\tStatus");
        System.out.println("------------------------------------------------------------");
        
        for (Tourist tourist : touristList) {
            System.out.printf("%s\t%s\t%s\t%s\n", 
                tourist.getId(), 
                tourist.getName().length() > 10 ? tourist.getName().substring(0, 10) + "..." : tourist.getName(),
                tourist.getEmail().length() > 20 ? tourist.getEmail().substring(0, 20) + "..." : tourist.getEmail(),
                tourist.getStatus());
        }
        System.out.println("============================================================\n");
    }
    
    /**
     * Prompts the user for confirmation before deleting a tourist account.
     * This implements step 2 of the use case flow.
     * 
     * @param touristId the ID of the tourist to be deleted
     * @return true if the user confirms deletion, false otherwise
     */
    public boolean confirmDeletion(String touristId) {
        Scanner scanner = new Scanner(System.in);
        
        // Find the tourist to display information
        Tourist touristToDelete = findTouristById(touristId);
        if (touristToDelete == null) {
            System.out.println("Error: Tourist with ID " + touristId + " not found.");
            return false;
        }
        
        System.out.println("\n===== CONFIRM DELETION =====");
        System.out.println("You are about to delete the following tourist account:");
        System.out.println("ID: " + touristToDelete.getId());
        System.out.println("Name: " + touristToDelete.getName());
        System.out.println("Email: " + touristToDelete.getEmail());
        System.out.println("Status: " + touristToDelete.getStatus());
        System.out.println("\nThis action cannot be undone.");
        System.out.print("Are you sure you want to delete this account? (yes/no): ");
        
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        return confirmation.equals("yes") || confirmation.equals("y");
    }
    
    /**
     * Deletes a tourist account from the system.
     * Implements step 4 of the use case flow.
     * Handles various edge cases such as invalid ID, connection issues, etc.
     * 
     * @param touristId the ID of the tourist to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteTouristAccount(String touristId) {
        // Edge case 1: Check server connection
        if (!isServerConnected()) {
            System.out.println("Error: Cannot delete tourist account. Server connection to ETOUR is interrupted.");
            return false;
        }
        
        // Edge case 2: Validate tourist ID
        if (touristId == null || touristId.trim().isEmpty()) {
            System.out.println("Error: Invalid tourist ID provided.");
            return false;
        }
        
        // Find and remove the tourist
        Iterator<Tourist> iterator = tourists.iterator();
        while (iterator.hasNext()) {
            Tourist tourist = iterator.next();
            if (tourist.getId().equals(touristId)) {
                // Edge case 3: Check if account is already deleted
                if ("DELETED".equals(tourist.getStatus())) {
                    System.out.println("Warning: Tourist account with ID " + touristId + " is already deleted.");
                    return false;
                }
                
                // Simulate deletion by changing status (in real app, would remove from database)
                tourist.setStatus("DELETED");
                iterator.remove(); // Remove from active list
                
                System.out.println("Success: Tourist account with ID " + touristId + " has been deleted.");
                System.out.println("Notification: The system has eliminated the selected tourist account.");
                
                // In a real application, you would:
                // 1. Call database delete operation
                // 2. Send notification to relevant systems
                // 3. Log the deletion for audit purposes
                
                return true;
            }
        }
        
        // Edge case 4: Tourist not found
        System.out.println("Error: Tourist with ID " + touristId + " not found.");
        return false;
    }
    
    /**
     * Helper method to find a tourist by ID.
     * 
     * @param touristId the ID of the tourist to find
     * @return the Tourist object if found, null otherwise
     */
    private Tourist findTouristById(String touristId) {
        for (Tourist tourist : tourists) {
            if (tourist.getId().equals(touristId)) {
                return tourist;
            }
        }
        return null;
    }
    
    /**
     * Gets the current count of active tourists (excluding deleted ones).
     * 
     * @return number of active tourists
     */
    public int getActiveTouristCount() {
        int count = 0;
        for (Tourist tourist : tourists) {
            if (!"DELETED".equals(tourist.getStatus())) {
                count++;
            }
        }
        return count;
    }
}
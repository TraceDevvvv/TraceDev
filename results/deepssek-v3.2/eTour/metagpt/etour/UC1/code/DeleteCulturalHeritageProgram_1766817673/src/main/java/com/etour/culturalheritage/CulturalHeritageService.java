package com.etour.culturalheritage;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Service class for CulturalHeritage deletion operations.
 * Handles the business logic for deleting cultural heritage objects with
 * confirmation, blocking multiple submissions, and handling connection interruptions.
 */
public class CulturalHeritageService {
    // In-memory storage for cultural heritage objects (simulating database)
    private final Map<String, CulturalHeritage> culturalHeritageStorage;
    
    // Track ongoing deletion operations to prevent multiple submissions
    private final Set<String> ongoingDeletions;
    
    // Track the server connection status (simulated)
    private volatile boolean serverConnected = true;
    
    /**
     * Constructor initializes storage with sample data.
     */
    public CulturalHeritageService() {
        this.culturalHeritageStorage = new ConcurrentHashMap<>();
        this.ongoingDeletions = ConcurrentHashMap.newKeySet();
        initializeSampleData();
    }
    
    /**
     * Initialize with sample cultural heritage data for demonstration.
     */
    private void initializeSampleData() {
        // Add some sample cultural heritage objects
        CulturalHeritage ch1 = new CulturalHeritage(
            "CH001", 
            "Ancient Temple", 
            "A 500-year-old temple with intricate carvings", 
            "Mountain Valley", 
            LocalDate.of(1520, 6, 15)
        );
        
        CulturalHeritage ch2 = new CulturalHeritage(
            "CH002", 
            "Royal Palace", 
            "Historic palace of the former kingdom", 
            "Capital City", 
            LocalDate.of(1680, 3, 22)
        );
        
        CulturalHeritage ch3 = new CulturalHeritage(
            "CH003", 
            "Traditional Village", 
            "Preserved traditional village showcasing local culture", 
            "Rural Province", 
            LocalDate.of(1850, 8, 10)
        );
        
        culturalHeritageStorage.put(ch1.getId(), ch1);
        culturalHeritageStorage.put(ch2.getId(), ch2);
        culturalHeritageStorage.put(ch3.getId(), ch3);
    }
    
    /**
     * Get all cultural heritage objects (simulating search results).
     * @return List of all cultural heritage objects
     */
    public List<CulturalHeritage> getAllCulturalHeritage() {
        checkServerConnection();
        return new ArrayList<>(culturalHeritageStorage.values());
    }
    
    /**
     * Get a specific cultural heritage object by ID.
     * @param id The ID of the cultural heritage
     * @return The CulturalHeritage object, or null if not found
     */
    public CulturalHeritage getCulturalHeritageById(String id) {
        checkServerConnection();
        return culturalHeritageStorage.get(id);
    }
    
    /**
     * Delete a cultural heritage object with confirmation and blocking mechanism.
     * This method implements the core deletion flow with multiple submission prevention.
     * 
     * @param culturalHeritageId The ID of the cultural heritage to delete
     * @param confirmationToken A unique token to track this specific deletion request
     * @return DeletionResult containing success status and message
     */
    public DeletionResult deleteCulturalHeritage(String culturalHeritageId, String confirmationToken) {
        // Check server connection first
        if (!isServerConnected()) {
            return new DeletionResult(false, "Cannot delete: Connection to ETOUR server interrupted.");
        }
        
        // Check if this deletion is already in progress (multiple submission prevention)
        if (!ongoingDeletions.add(culturalHeritageId)) {
            return new DeletionResult(false, 
                "Deletion already in progress for cultural heritage ID: " + culturalHeritageId + 
                ". Please wait for the current operation to complete.");
        }
        
        try {
            // Validate the cultural heritage exists
            if (!culturalHeritageStorage.containsKey(culturalHeritageId)) {
                return new DeletionResult(false, 
                    "Cultural heritage not found with ID: " + culturalHeritageId);
            }
            
            // Simulate server communication delay
            simulateServerCommunication();
            
            // Perform the deletion
            CulturalHeritage deleted = culturalHeritageStorage.remove(culturalHeritageId);
            
            if (deleted != null) {
                return new DeletionResult(true, 
                    "Successfully deleted cultural heritage: " + deleted.getName() + 
                    " (ID: " + culturalHeritageId + ")");
            } else {
                return new DeletionResult(false, 
                    "Failed to delete cultural heritage with ID: " + culturalHeritageId);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new DeletionResult(false, 
                "Deletion interrupted: " + e.getMessage());
        } catch (Exception e) {
            return new DeletionResult(false, 
                "Error during deletion: " + e.getMessage());
        } finally {
            // Always remove from ongoing deletions to allow future operations
            ongoingDeletions.remove(culturalHeritageId);
        }
    }
    
    /**
     * Simulate confirmation of deletion (separate from actual deletion).
     * This method would typically validate the confirmation token in a real system.
     * 
     * @param culturalHeritageId The ID to confirm deletion for
     * @param confirmationToken The confirmation token
     * @return true if confirmation is valid, false otherwise
     */
    public boolean confirmDeletion(String culturalHeritageId, String confirmationToken) {
        // In a real system, this would validate the token (e.g., check against session or database)
        // For this demo, we'll simply check that the cultural heritage exists
        return culturalHeritageStorage.containsKey(culturalHeritageId) && 
               confirmationToken != null && !confirmationToken.isEmpty();
    }
    
    /**
     * Check if a deletion operation is currently in progress for the given ID.
     * Used by UI to block input controls.
     * 
     * @param culturalHeritageId The ID to check
     * @return true if deletion is in progress, false otherwise
     */
    public boolean isDeletionInProgress(String culturalHeritageId) {
        return ongoingDeletions.contains(culturalHeritageId);
    }
    
    /**
     * Get the current server connection status.
     * @return true if server is connected, false otherwise
     */
    public synchronized boolean isServerConnected() {
        return serverConnected;
    }
    
    /**
     * Set the server connection status (for simulation purposes).
     * @param connected true if server is connected, false otherwise
     */
    public synchronized void setServerConnected(boolean connected) {
        this.serverConnected = connected;
        if (!connected) {
            System.out.println("[SERVER] Connection to ETOUR server lost.");
        } else {
            System.out.println("[SERVER] Connection to ETOUR server restored.");
        }
    }
    
    /**
     * Check server connection and throw exception if disconnected.
     * @throws IllegalStateException if server is not connected
     */
    private void checkServerConnection() {
        if (!serverConnected) {
            throw new IllegalStateException("Connection to ETOUR server interrupted.");
        }
    }
    
    /**
     * Simulate server communication with a small delay.
     * @throws InterruptedException if the thread is interrupted during sleep
     */
    private void simulateServerCommunication() throws InterruptedException {
        // Simulate network delay (500ms to 1.5s)
        Thread.sleep(500 + (long)(Math.random() * 1000));
        
        // Randomly simulate server disconnection (10% chance for demo purposes)
        if (Math.random() < 0.1) {
            serverConnected = false;
            throw new IllegalStateException("Connection to ETOUR server interrupted during operation.");
        }
    }
    
    /**
     * Add a new cultural heritage for testing purposes.
     * @param culturalHeritage The cultural heritage to add
     */
    public void addCulturalHeritage(CulturalHeritage culturalHeritage) {
        culturalHeritageStorage.put(culturalHeritage.getId(), culturalHeritage);
    }
    
    /**
     * Get the count of cultural heritage objects.
     * @return The number of cultural heritage objects
     */
    public int getCulturalHeritageCount() {
        return culturalHeritageStorage.size();
    }
    
    /**
     * Inner class to represent deletion result with status and message.
     */
    public static class DeletionResult {
        private final boolean success;
        private final String message;
        
        public DeletionResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
        }
        
        @Override
        public String toString() {
            return "DeletionResult{" +
                   "success=" + success +
                   ", message='" + message + '\'' +
                   '}';
        }
    }
}
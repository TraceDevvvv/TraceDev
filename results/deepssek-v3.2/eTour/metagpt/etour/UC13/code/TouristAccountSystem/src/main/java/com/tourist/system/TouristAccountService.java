package com.tourist.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Service class for managing tourist accounts with methods to enable/disable accounts
 * and handle server connection exceptions.
 * 
 * This class simulates interaction with the ETOUR server and handles edge cases
 * like server connection interruptions.
 */
public class TouristAccountService {
    // In-memory storage for tourists (simulating database)
    private Map<String, Tourist> touristDatabase;
    private Random random;
    
    /**
     * Constructor initializes the service with sample data
     */
    public TouristAccountService() {
        this.touristDatabase = new HashMap<>();
        this.random = new Random();
        initializeSampleData();
    }
    
    /**
     * Initialize sample tourist data for demonstration
     */
    private void initializeSampleData() {
        touristDatabase.put("T001", new Tourist("T001", "John Smith", "john.smith@example.com", true));
        touristDatabase.put("T002", new Tourist("T002", "Emma Johnson", "emma.j@example.com", false));
        touristDatabase.put("T003", new Tourist("T003", "Carlos Rodriguez", "carlos.r@example.com", true));
        touristDatabase.put("T004", new Tourist("T004", "Lisa Wang", "lisa.wang@example.com", true));
        touristDatabase.put("T005", new Tourist("T005", "Ahmed Hassan", "ahmed.h@example.com", false));
    }
    
    /**
     * Simulates checking server connection to ETOUR
     * @return true if server is reachable, false if connection interrupted
     */
    public boolean checkServerConnection() {
        // Simulate random server connectivity issues (10% chance of failure)
        return random.nextInt(100) >= 10; // 90% success rate
    }
    
    /**
     * Simulates a delay to mimic network/server communication
     * @throws ServerConnectionException if connection fails during operation
     */
    private void simulateServerCommunication() throws ServerConnectionException {
        // Check server connection before operation
        if (!checkServerConnection()) {
            throw new ServerConnectionException("ETOUR server connection interrupted");
        }
        
        // Simulate network delay
        try {
            Thread.sleep(100 + random.nextInt(200)); // 100-300ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServerConnectionException("Server communication interrupted", e);
        }
    }
    
    /**
     * Finds a tourist by ID
     * @param touristId The ID of the tourist to find
     * @return Tourist object if found, null otherwise
     * @throws ServerConnectionException if server connection fails
     */
    public Tourist findTouristById(String touristId) throws ServerConnectionException {
        if (touristId == null || touristId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty");
        }
        
        simulateServerCommunication();
        return touristDatabase.get(touristId);
    }
    
    /**
     * Activates a tourist account
     * @param touristId The ID of the tourist to activate
     * @return true if operation successful, false if tourist not found
     * @throws ServerConnectionException if server connection fails
     */
    public boolean activateTouristAccount(String touristId) throws ServerConnectionException {
        if (touristId == null || touristId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty");
        }
        
        simulateServerCommunication();
        Tourist tourist = touristDatabase.get(touristId);
        
        if (tourist == null) {
            return false;
        }
        
        tourist.activateAccount();
        return true;
    }
    
    /**
     * Deactivates a tourist account
     * @param touristId The ID of the tourist to deactivate
     * @return true if operation successful, false if tourist not found
     * @throws ServerConnectionException if server connection fails
     */
    public boolean deactivateTouristAccount(String touristId) throws ServerConnectionException {
        if (touristId == null || touristId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty");
        }
        
        simulateServerCommunication();
        Tourist tourist = touristDatabase.get(touristId);
        
        if (tourist == null) {
            return false;
        }
        
        tourist.deactivateAccount();
        return true;
    }
    
    /**
     * Toggles tourist account status (activates if inactive, deactivates if active)
     * @param touristId The ID of the tourist
     * @return The new status after toggling, or null if tourist not found
     * @throws ServerConnectionException if server connection fails
     */
    public Boolean toggleTouristAccountStatus(String touristId) throws ServerConnectionException {
        if (touristId == null || touristId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty");
        }
        
        simulateServerCommunication();
        Tourist tourist = touristDatabase.get(touristId);
        
        if (tourist == null) {
            return null;
        }
        
        boolean newStatus = tourist.toggleAccountStatus();
        return newStatus;
    }
    
    /**
     * Gets the current status of a tourist account
     * @param touristId The ID of the tourist
     * @return Account status (true for active, false for inactive), or null if tourist not found
     * @throws ServerConnectionException if server connection fails
     */
    public Boolean getTouristAccountStatus(String touristId) throws ServerConnectionException {
        if (touristId == null || touristId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tourist ID cannot be null or empty");
        }
        
        simulateServerCommunication();
        Tourist tourist = touristDatabase.get(touristId);
        
        if (tourist == null) {
            return null;
        }
        
        return tourist.isActive();
    }
    
    /**
     * Gets all tourists in the system
     * @return List of all tourists
     * @throws ServerConnectionException if server connection fails
     */
    public List<Tourist> getAllTourists() throws ServerConnectionException {
        simulateServerConnection();
        return new ArrayList<>(touristDatabase.values());
    }
    
    /**
     * Checks server connection and throws exception if not available
     * @throws ServerConnectionException if server connection fails
     */
    public void simulateServerConnection() throws ServerConnectionException {
        if (!checkServerConnection()) {
            throw new ServerConnectionException("ETOUR server connection interrupted");
        }
    }
    
    /**
     * Adds a new tourist to the system (for testing/demo purposes)
     * @param tourist The tourist to add
     * @return true if added successfully, false if tourist with same ID already exists
     * @throws ServerConnectionException if server connection fails
     */
    public boolean addTourist(Tourist tourist) throws ServerConnectionException {
        if (tourist == null) {
            throw new IllegalArgumentException("Tourist cannot be null");
        }
        
        simulateServerCommunication();
        
        if (touristDatabase.containsKey(tourist.getId())) {
            return false;
        }
        
        touristDatabase.put(tourist.getId(), tourist);
        return true;
    }
    
    /**
     * Custom exception for server connection issues
     */
    public static class ServerConnectionException extends Exception {
        public ServerConnectionException(String message) {
            super(message);
        }
        
        public ServerConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
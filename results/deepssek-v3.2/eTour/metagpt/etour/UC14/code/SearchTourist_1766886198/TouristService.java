// TouristService.java
// This class simulates the server connection to the ETOUR system and provides
// search functionality for tourist accounts. It handles connection interruptions
// and manages a mock database of tourist accounts for demonstration purposes.

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Service class that simulates interaction with the ETOUR server.
 * Provides methods to search tourist accounts with given parameters,
 * handling potential server connection interruptions.
 */
public class TouristService {
    
    // Simulated server connection status
    private boolean serverConnected;
    
    // Mock database of tourist accounts
    private List<TouristAccount> touristDatabase;
    
    // Random generator for simulating connection failures
    private Random random;
    
    // Connection failure probability (for simulation)
    private static final double CONNECTION_FAILURE_PROBABILITY = 0.2;
    
    /**
     * Constructor initializes the service with mock data and establishes connection.
     */
    public TouristService() {
        this.serverConnected = true;
        this.random = new Random();
        this.touristDatabase = new ArrayList<>();
        initializeMockData();
    }
    
    /**
     * Initializes the mock database with sample tourist accounts.
     */
    private void initializeMockData() {
        // Create sample tourist accounts
        touristDatabase.add(new TouristAccount("TA001", "john_doe", "password123", 
                "John", "Doe", "john.doe@example.com", "+1234567890", 
                "USA", LocalDate.of(1985, 5, 15), "P12345678"));
        
        touristDatabase.add(new TouristAccount("TA002", "jane_smith", "password456", 
                "Jane", "Smith", "jane.smith@example.com", "+0987654321", 
                "UK", LocalDate.of(1990, 8, 22), "P87654321"));
        
        touristDatabase.add(new TouristAccount("TA003", "robert_j", "password789", 
                "Robert", "Johnson", "robert.j@example.com", "+1122334455", 
                "Canada", LocalDate.of(1978, 3, 10), "P11223344"));
        
        touristDatabase.add(new TouristAccount("TA004", "maria_g", "password101", 
                "Maria", "Garcia", "maria.g@example.com", "+5566778899", 
                "Spain", LocalDate.of(1992, 11, 30), "P55667788"));
        
        touristDatabase.add(new TouristAccount("TA005", "li_wei", "password202", 
                "Li", "Wei", "li.wei@example.com", "+9988776655", 
                "China", LocalDate.of(1988, 7, 18), "P99887766"));
        
        touristDatabase.add(new TouristAccount("TA006", "anna_k", "password303", 
                "Anna", "Kowalski", "anna.k@example.com", "+4433221100", 
                "Poland", LocalDate.of(1995, 2, 25), "P44332211"));
        
        touristDatabase.add(new TouristAccount("TA007", "john_smith", "password404", 
                "John", "Smith", "john.smith@example.com", "+6677889900", 
                "Australia", LocalDate.of(1982, 9, 5), "P66778899"));
        
        touristDatabase.add(new TouristAccount("TA008", "sarah_m", "password505", 
                "Sarah", "Miller", "sarah.m@example.com", "+7788990011", 
                "USA", LocalDate.of(1991, 4, 12), "P77889900"));
        
        // Mark some accounts as inactive
        touristDatabase.get(2).setActive(false);
        touristDatabase.get(5).setActive(false);
    }
    
    /**
     * Simulates checking the server connection status.
     * Randomly simulates connection failures based on probability.
     * @return true if server is connected, false otherwise
     */
    public boolean checkServerConnection() {
        // Simulate random connection failure
        if (random.nextDouble() < CONNECTION_FAILURE_PROBABILITY) {
            serverConnected = false;
            return false;
        }
        serverConnected = true;
        return true;
    }
    
    /**
     * Attempts to reconnect to the server.
     * @return true if reconnection successful, false otherwise
     */
    public boolean reconnectToServer() {
        System.out.println("Attempting to reconnect to ETOUR server...");
        
        // Simulate reconnection attempt with 80% success rate
        if (random.nextDouble() < 0.8) {
            serverConnected = true;
            System.out.println("Reconnection successful.");
            return true;
        } else {
            System.out.println("Reconnection failed. Please try again later.");
            return false;
        }
    }
    
    /**
     * Searches for tourist accounts based on the given search parameters.
     * Handles server connection interruptions and retries.
     * @param parameters The search criteria
     * @return List of tourist accounts matching the criteria, or empty list if none found
     * @throws ServerConnectionException if server connection cannot be established
     */
    public List<TouristAccount> searchTouristAccounts(SearchParameters parameters) throws ServerConnectionException {
        // Check server connection
        if (!checkServerConnection()) {
            System.out.println("Warning: Server connection interrupted. Attempting to reconnect...");
            
            // Try to reconnect once
            if (!reconnectToServer()) {
                throw new ServerConnectionException("Unable to connect to ETOUR server. Please check your connection and try again.");
            }
        }
        
        // If search parameters are empty, return all accounts (or handle as needed)
        if (parameters == null || parameters.isEmpty()) {
            System.out.println("No search criteria specified. Returning all tourist accounts.");
            return new ArrayList<>(touristDatabase);
        }
        
        // Perform search
        List<TouristAccount> results = new ArrayList<>();
        for (TouristAccount account : touristDatabase) {
            if (parameters.matches(account)) {
                results.add(account);
            }
        }
        
        return results;
    }
    
    /**
     * Adds a new tourist account to the mock database (for testing purposes).
     * @param account The tourist account to add
     */
    public void addTouristAccount(TouristAccount account) {
        touristDatabase.add(account);
    }
    
    /**
     * Returns the total number of tourist accounts in the mock database.
     * @return count of tourist accounts
     */
    public int getTotalAccountCount() {
        return touristDatabase.size();
    }
    
    /**
     * Returns all tourist accounts in the mock database.
     * @return list of all tourist accounts
     */
    public List<TouristAccount> getAllTouristAccounts() {
        return new ArrayList<>(touristDatabase);
    }
    
    /**
     * Custom exception for server connection issues.
     */
    public static class ServerConnectionException extends Exception {
        public ServerConnectionException(String message) {
            super(message);
        }
        
        public ServerConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * Main method for testing the TouristService class.
     * This is for demonstration purposes only.
     */
    public static void main(String[] args) {
        TouristService service = new TouristService();
        
        // Test search with specific parameters
        SearchParameters params = new SearchParameters();
        params.setNationality("USA");
        
        try {
            List<TouristAccount> results = service.searchTouristAccounts(params);
            System.out.println("Found " + results.size() + " tourist account(s):");
            for (TouristAccount account : results) {
                System.out.println("  - " + account.getFullName() + " (" + account.getEmail() + ")");
            }
        } catch (ServerConnectionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
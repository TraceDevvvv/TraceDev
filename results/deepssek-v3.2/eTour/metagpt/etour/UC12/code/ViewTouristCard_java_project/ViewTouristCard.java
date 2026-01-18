package com.etour;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ViewTouristCard.java
 * This class handles the ViewTouristCard use case for displaying tourist details.
 * It simulates server communication with the ETOUR server and provides error handling
 * for server connection interruptions.
 * 
 * The class follows the use case flow:
 * 1. Receives a tourist ID from a list (obtained from SearchTourist use case)
 * 2. Fetches tourist data from the ETOUR server
 * 3. Displays the tourist card with all details
 * 
 * Quality requirements: Handles server connection interruptions gracefully.
 */
public class ViewTouristCard {
    
    /**
     * Simulates a connection to the ETOUR server.
     * In a real application, this would be replaced with actual API calls.
     */
    private static class ETourServerConnection {
        // Simulating server state
        private boolean serverAvailable = true;
        private int simulatedLatencyMs = 100;
        
        /**
         * Sets server availability for testing purposes.
         * @param available true if server is available, false to simulate downtime
         */
        public void setServerAvailable(boolean available) {
            this.serverAvailable = available;
        }
        
        /**
         * Sets simulated latency for testing.
         * @param latencyMs latency in milliseconds
         */
        public void setSimulatedLatency(int latencyMs) {
            this.simulatedLatencyMs = latencyMs;
        }
        
        /**
         * Simulates fetching tourist data from the ETOUR server.
         * @param touristId the ID of the tourist to fetch
         * @return Tourist object if found, null if not found or on error
         * @throws ServerConnectionException if server connection fails
         */
        public Tourist fetchTouristData(String touristId) throws ServerConnectionException {
            // Simulate network latency
            try {
                Thread.sleep(simulatedLatencyMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new ServerConnectionException("Connection interrupted", e);
            }
            
            // Check if server is available
            if (!serverAvailable) {
                throw new ServerConnectionException("ETOUR server is currently unavailable. Please try again later.");
            }
            
            // Simulate fetching data from database/API
            // In a real application, this would make an actual API call
            if (touristId == null || touristId.trim().isEmpty()) {
                throw new IllegalArgumentException("Tourist ID cannot be null or empty");
            }
            
            // Return simulated data based on touristId
            return createSimulatedTouristData(touristId);
        }
        
        /**
         * Creates simulated tourist data for demonstration.
         * In a real application, this would be fetched from a database.
         * @param touristId the tourist ID to create data for
         * @return simulated Tourist object
         */
        private Tourist createSimulatedTouristData(String touristId) {
            // Create different tourist profiles based on ID for demonstration
            switch (touristId) {
                case "T001":
                    return new Tourist("T001", "John", "Doe", "john.doe@example.com",
                            "+1-555-123-4567", "123 Main St", "New York", "USA",
                            "American", java.time.LocalDate.of(1985, 5, 15), "P12345678",
                            java.time.LocalDate.of(2020, 3, 10), java.time.LocalDate.of(2024, 1, 20),
                            true, "AG001");
                case "T002":
                    return new Tourist("T002", "Maria", "Garcia", "maria.garcia@example.com",
                            "+34-91-876-5432", "456 Gran Via", "Madrid", "Spain",
                            "Spanish", java.time.LocalDate.of(1990, 8, 22), "P87654321",
                            java.time.LocalDate.of(2021, 6, 15), java.time.LocalDate.of(2024, 2, 10),
                            true, "AG001");
                case "T003":
                    return new Tourist("T003", "Kenji", "Tanaka", "kenji.tanaka@example.com",
                            "+81-3-1234-5678", "789 Shinjuku", "Tokyo", "Japan",
                            "Japanese", java.time.LocalDate.of(1978, 11, 30), "P98765432",
                            java.time.LocalDate.of(2019, 9, 5), java.time.LocalDate.of(2023, 12, 1),
                            false, "AG002");
                default:
                    // Return generic tourist for any other ID
                    return new Tourist(touristId, "FirstName", "LastName", "email@example.com",
                            "+000-000-0000", "Address", "City", "Country");
            }
        }
        
        /**
         * Simulates fetching multiple tourists for the SearchTourist use case.
         * @param searchTerm optional search term
         * @return list of tourist summaries
         */
        public List<Tourist> searchTourists(String searchTerm) {
            List<Tourist> tourists = new ArrayList<>();
            tourists.add(createSimulatedTouristData("T001"));
            tourists.add(createSimulatedTouristData("T002"));
            tourists.add(createSimulatedTouristData("T003"));
            
            // Filter by search term if provided
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                List<Tourist> filtered = new ArrayList<>();
                String term = searchTerm.toLowerCase();
                for (Tourist t : tourists) {
                    if (t.getFirstName().toLowerCase().contains(term) ||
                        t.getLastName().toLowerCase().contains(term) ||
                        t.getEmail().toLowerCase().contains(term) ||
                        t.getTouristId().toLowerCase().contains(term)) {
                        filtered.add(t);
                    }
                }
                return filtered;
            }
            
            return tourists;
        }
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
    
    private ETourServerConnection serverConnection;
    private ExecutorService executorService;
    
    /**
     * Constructor initializes server connection and thread pool.
     */
    public ViewTouristCard() {
        this.serverConnection = new ETourServerConnection();
        this.executorService = Executors.newFixedThreadPool(3);
    }
    
    /**
     * Main method to display tourist card.
     * This follows the use case flow described in the requirements.
     * 
     * @param touristId the ID of the tourist to display
     * @return true if successful, false otherwise
     */
    public boolean displayTouristCard(String touristId) {
        if (touristId == null || touristId.trim().isEmpty()) {
            System.err.println("Error: Tourist ID cannot be null or empty.");
            return false;
        }
        
        System.out.println("=== View Tourist Card ===");
        System.out.println("Fetching data for tourist ID: " + touristId);
        System.out.println("Connecting to ETOUR server...");
        
        try {
            // Use a timeout to handle slow or unresponsive server
            Tourist tourist = fetchTouristWithTimeout(touristId, 5); // 5 second timeout
            
            if (tourist != null) {
                // Display the tourist card
                System.out.println("\n" + tourist.toDisplayString());
                System.out.println("=== End of Tourist Card ===");
                return true;
            } else {
                System.err.println("Error: Tourist not found with ID: " + touristId);
                return false;
            }
            
        } catch (ServerConnectionException e) {
            handleServerConnectionError(e);
            return false;
        } catch (InterruptedException e) {
            System.err.println("Operation was interrupted.");
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Fetches tourist data with a timeout to handle unresponsive server.
     * 
     * @param touristId the tourist ID to fetch
     * @param timeoutSeconds timeout in seconds
     * @return Tourist object if found, null if not found
     * @throws ServerConnectionException if server connection fails
     * @throws InterruptedException if thread is interrupted
     */
    private Tourist fetchTouristWithTimeout(String touristId, int timeoutSeconds) 
            throws ServerConnectionException, InterruptedException {
        
        Callable<Tourist> task = () -> serverConnection.fetchTouristData(touristId);
        Future<Tourist> future = executorService.submit(task);
        
        try {
            // Wait for the result with timeout
            return future.get(timeoutSeconds, TimeUnit.SECONDS);
        } catch (java.util.concurrent.TimeoutException e) {
            // Cancel the task if it times out
            future.cancel(true);
            throw new ServerConnectionException(
                "Connection timeout: ETOUR server did not respond within " + timeoutSeconds + " seconds.");
        } catch (java.util.concurrent.ExecutionException e) {
            // Unwrap the actual exception
            Throwable cause = e.getCause();
            if (cause instanceof ServerConnectionException) {
                throw (ServerConnectionException) cause;
            } else if (cause instanceof IllegalArgumentException) {
                throw (IllegalArgumentException) cause;
            } else {
                throw new ServerConnectionException("Failed to fetch tourist data: " + cause.getMessage(), cause);
            }
        }
    }
    
    /**
     * Handles server connection errors gracefully.
     * Provides user-friendly messages and recovery suggestions.
     * 
     * @param e the ServerConnectionException that occurred
     */
    private void handleServerConnectionError(ServerConnectionException e) {
        System.err.println("\n❌ SERVER CONNECTION ERROR");
        System.err.println("Message: " + e.getMessage());
        System.err.println("\nTroubleshooting steps:");
        System.err.println("1. Check your internet connection");
        System.err.println("2. Verify ETOUR server status");
        System.err.println("3. Try again in a few moments");
        System.err.println("4. Contact system administrator if problem persists");
        
        // Log the technical details for debugging
        System.err.println("\n[Technical Details] " + e.getClass().getName());
        if (e.getCause() != null) {
            System.err.println("Caused by: " + e.getCause().getMessage());
        }
    }
    
    /**
     * Simulates the SearchTourist use case to get a list of tourists.
     * This method would typically be in a separate SearchTourist class,
     * but it's included here for completeness.
     * 
     * @param searchTerm optional search term for filtering
     * @return list of tourist summaries
     */
    public List<Tourist> searchTourists(String searchTerm) {
        try {
            // Simulate server call with delay
            Thread.sleep(200);
            return serverConnection.searchTourists(searchTerm);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error searching tourists: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * Displays a summary list of tourists (for SearchTourist use case integration).
     * 
     * @param tourists list of tourists to display
     */
    public void displayTouristList(List<Tourist> tourists) {
        if (tourists == null || tourists.isEmpty()) {
            System.out.println("No tourists found.");
            return;
        }
        
        System.out.println("\n=== Tourist List ===");
        System.out.println("ID\tName\t\tEmail\t\t\tStatus");
        System.out.println("---------------------------------------------------");
        
        for (Tourist tourist : tourists) {
            System.out.printf("%s\t%s %s\t%s\t%s\n",
                tourist.getTouristId(),
                tourist.getFirstName(),
                tourist.getLastName(),
                tourist.getEmail().length() > 15 ? 
                    tourist.getEmail().substring(0, 12) + "..." : tourist.getEmail(),
                tourist.isActive() ? "Active" : "Inactive"
            );
        }
        System.out.println("=== End of List (Select ID to view details) ===\n");
    }
    
    /**
     * Simulates agency login (entry condition from use case).
     * In a real system, this would validate credentials.
     * 
     * @param agencyId the agency ID
     * @param password the password (not actually validated in simulation)
     * @return true if login successful
     */
    public boolean agencyLogin(String agencyId, String password) {
        if (agencyId == null || agencyId.trim().isEmpty()) {
            System.err.println("Agency ID cannot be empty.");
            return false;
        }
        
        // Simulate login process
        System.out.println("Logging in agency: " + agencyId);
        try {
            Thread.sleep(500); // Simulate authentication delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        System.out.println("Login successful for agency: " + agencyId);
        return true;
    }
    
    /**
     * Cleanup method to release resources.
     */
    public void shutdown() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Main demonstration method that simulates the complete use case flow.
     * This is for testing and demonstration purposes.
     */
    public void demonstrateUseCase() {
        System.out.println("=== ETOUR System - View Tourist Card Use Case ===\n");
        
        // Step 1: Agency login (entry condition)
        if (!agencyLogin("AG001", "password123")) {
            System.err.println("Cannot proceed: Agency login failed.");
            return;
        }
        
        // Step 2: Search tourists (simulating SearchTourist use case)
        System.out.println("\n--- Searching Tourists ---");
        List<Tourist> tourists = searchTourists("");
        displayTouristList(tourists);
        
        // Step 3: Select a tourist from the list (use case step 1)
        System.out.println("Selecting tourist T002 from the list...");
        
        // Step 4: Display tourist card (use case step 2)
        System.out.println("\n--- Displaying Tourist Card ---");
        boolean success = displayTouristCard("T002");
        
        if (success) {
            System.out.println("\n✅ Use case completed successfully.");
        } else {
            System.out.println("\n❌ Use case failed. See error messages above.");
        }
        
        // Demonstrate error handling
        System.out.println("\n--- Demonstrating Error Handling ---");
        System.out.println("Testing server connection interruption...");
        
        // Simulate server downtime
        serverConnection.setServerAvailable(false);
        displayTouristCard("T001");
        
        // Restore server
        serverConnection.setServerAvailable(true);
    }
}
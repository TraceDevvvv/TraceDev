package com.etop.agency;

/**
 * Simulates interaction with the ETOUR server for tourist account deletion.
 * This class provides a mock implementation of server-side operations
 * related to tourist accounts, specifically deletion.
 */
public class ETOURServer {

    /**
     * Simulates the deletion of a tourist account on the ETOUR server.
     * This method introduces a simulated delay and can randomly fail
     * to mimic real-world network or server issues.
     *
     * @param touristId The ID of the tourist account to be deleted.
     * @return true if the deletion was successful, false otherwise (e.g., server error, tourist not found).
     */
    public boolean deleteTouristAccount(String touristId) {
        System.out.println("ETOUR Server: Attempting to delete tourist account with ID: " + touristId);

        // Simulate network delay
        try {
            Thread.sleep(1000); // Simulate 1 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("ETOUR Server: Deletion interrupted for tourist ID: " + touristId);
            return false;
        }

        // Simulate server errors or "tourist not found" scenarios
        // For demonstration, let's say deletion fails 20% of the time
        if (Math.random() < 0.2) {
            System.err.println("ETOUR Server: Failed to delete tourist account " + touristId + " due to a simulated server error or connection interruption.");
            return false;
        }

        // Simulate "tourist not found" for a specific ID
        if ("T000".equals(touristId)) {
            System.err.println("ETOUR Server: Tourist account with ID " + touristId + " not found.");
            return false;
        }

        System.out.println("ETOUR Server: Successfully deleted tourist account with ID: " + touristId);
        return true;
    }

    /**
     * Simulates checking the connection status to the ETOUR server.
     *
     * @return true if the connection is active, false if interrupted.
     */
    public boolean isConnected() {
        // For simplicity, always return true unless a specific scenario is needed to simulate disconnection.
        // In a real application, this would involve actual network checks.
        return true;
    }
}
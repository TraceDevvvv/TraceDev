'''
Implementation of TouristDAO with mock data for demonstration.
Simulates database operations and server connection.
'''
package com.etour.agency;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class TouristDAOImpl implements TouristDAO {
    private List<Tourist> tourists;
    private Random random;
    private boolean connectionAvailable;
    public TouristDAOImpl() {
        this.tourists = new ArrayList<>();
        this.random = new Random();
        this.connectionAvailable = true;
        initializeMockData();
    }
    /**
     * Initialize mock tourist data for demonstration
     */
    private void initializeMockData() {
        tourists.add(new Tourist("TOUR001", "John", "Doe", "john.doe@example.com", 
                                "+1-555-0101", "American", "A12345678", 
                                "123 Main St, New York, USA", "1985-05-15", "+1-555-0102"));
        tourists.add(new Tourist("TOUR002", "Maria", "Garcia", "maria.garcia@example.com", 
                                "+34-910-123456", "Spanish", "B98765432", 
                                "Calle Mayor 1, Madrid, Spain", "1990-08-22", "+34-910-987654"));
        tourists.add(new Tourist("TOUR003", "Kenji", "Tanaka", "kenji.tanaka@example.com", 
                                "+81-3-1234-5678", "Japanese", "C55555555", 
                                "Shibuya 1-2-3, Tokyo, Japan", "1988-12-03", "+81-3-8765-4321"));
        tourists.add(new Tourist("TOUR004", "Sophie", "Martin", "sophie.martin@example.com", 
                                "+33-1-2345-6789", "French", "D44444444", 
                                "Rue de Rivoli 25, Paris, France", "1992-03-18", "+33-1-9876-5432"));
        tourists.add(new Tourist("TOUR005", "Ahmed", "Khan", "ahmed.khan@example.com", 
                                "+92-21-9876543", "Pakistani", "E33333333", 
                                "Block 5, Karachi, Pakistan", "1987-07-30", "+92-21-1234567"));
    }
    @Override
    public List<Tourist> getAllTourists() {
        // Simulate potential connection interruption
        if (!isConnected()) {
            throw new RuntimeException("Connection to ETOUR server interrupted");
        }
        return new ArrayList<>(tourists);
    }
    @Override
    public List<Tourist> searchTourists(String query) {
        if (!isConnected()) {
            throw new RuntimeException("Connection to ETOUR server interrupted");
        }
        List<Tourist> results = new ArrayList<>();
        String lowercaseQuery = query.toLowerCase();
        for (Tourist tourist : tourists) {
            if (tourist.getFirstName().toLowerCase().contains(lowercaseQuery) ||
                tourist.getLastName().toLowerCase().contains(lowercaseQuery) ||
                tourist.getEmail().toLowerCase().contains(lowercaseQuery) ||
                tourist.getPassportNumber().toLowerCase().contains(lowercaseQuery)) {
                results.add(tourist);
            }
        }
        return results;
    }
    @Override
    public Tourist getTouristById(String id) {
        if (!isConnected()) {
            throw new RuntimeException("Connection to ETOUR server interrupted");
        }
        for (Tourist tourist : tourists) {
            if (tourist.getId().equals(id)) {
                return tourist;
            }
        }
        return null;
    }
    @Override
    public boolean updateTourist(Tourist updatedTourist) {
        if (!isConnected()) {
            throw new RuntimeException("Connection to ETOUR server interrupted");
        }
        for (int i = 0; i < tourists.size(); i++) {
            Tourist tourist = tourists.get(i);
            if (tourist.getId().equals(updatedTourist.getId())) {
                tourists.set(i, updatedTourist);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isConnected() {
        // Simulate random connection failures (10% chance) for demonstration
        boolean wasConnected = connectionAvailable;
        if (random.nextInt(100) < 10) {
            connectionAvailable = false;
        } else {
            connectionAvailable = true;
        }
        // Log connection state change for debugging
        if (wasConnected != connectionAvailable) {
            String state = connectionAvailable ? "restored" : "lost";
            System.out.println("ETOUR connection " + state + " at: " + System.currentTimeMillis());
        }
        return connectionAvailable;
    }
    /**
     * Simulate reconnection to server
     */
    public void reconnect() {
        System.out.println("Attempting to reconnect to ETOUR server...");
        connectionAvailable = true;
        System.out.println("ETOUR connection restored successfully");
    }
}
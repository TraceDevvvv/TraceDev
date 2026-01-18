package com.example.culturalheritage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Manages cultural heritage objects, including searching and deleting them.
 * This service simulates interactions with a backend system (e.g., a database or an ETOUR server).
 */
public class CulturalHeritageService {
    // A simple in-memory list to simulate a database of cultural heritage objects.
    private final List<CulturalHeritage> culturalHeritageDatabase;
    private boolean etourServerConnected = true; // Simulates connection status to ETOUR server

    /**
     * Constructs a new CulturalHeritageService and initializes it with some sample data.
     */
    public CulturalHeritageService() {
        culturalHeritageDatabase = new ArrayList<>();
        // Add some sample cultural heritage objects
        culturalHeritageDatabase.add(new CulturalHeritage("CH001", "Eiffel Tower"));
        culturalHeritageDatabase.add(new CulturalHeritage("CH002", "Great Wall of China"));
        culturalHeritageDatabase.add(new CulturalHeritage("CH003", "Pyramids of Giza"));
        culturalHeritageDatabase.add(new CulturalHeritage("CH004", "Colosseum"));
    }

    /**
     * Simulates searching for cultural heritage objects.
     * In a real application, this would query a database based on search criteria.
     * For this use case, it returns all available cultural heritage objects.
     *
     * @return A list of all cultural heritage objects.
     * @throws ETOURConnectionException if the connection to the ETOUR server is interrupted.
     */
    public List<CulturalHeritage> searchCulturalHeritage() throws ETOURConnectionException {
        checkETOURConnection();
        // Simulate network delay
        simulateNetworkDelay();
        System.out.println("System: Retrieving list of cultural heritage objects...");
        return new ArrayList<>(culturalHeritageDatabase); // Return a copy to prevent external modification
    }

    /**
     * Deletes a cultural heritage object from the system.
     *
     * @param culturalHeritageId The ID of the cultural heritage object to delete.
     * @return true if the cultural heritage was successfully deleted, false otherwise (e.g., not found).
     * @throws ETOURConnectionException if the connection to the ETOUR server is interrupted.
     */
    public boolean deleteCulturalHeritage(String culturalHeritageId) throws ETOURConnectionException {
        checkETOURConnection();
        // Simulate network delay for deletion
        simulateNetworkDelay();

        Optional<CulturalHeritage> culturalHeritageOptional = culturalHeritageDatabase.stream()
                .filter(ch -> ch.getId().equals(culturalHeritageId))
                .findFirst();

        if (culturalHeritageOptional.isPresent()) {
            culturalHeritageDatabase.remove(culturalHeritageOptional.get());
            System.out.println("System: Cultural Heritage with ID '" + culturalHeritageId + "' successfully deleted.");
            return true;
        } else {
            System.out.println("System: Cultural Heritage with ID '" + culturalHeritageId + "' not found.");
            return false;
        }
    }

    /**
     * Simulates an interruption of the connection to the ETOUR server.
     * This is for testing the "Interruption of connection to the server ETOUR" exit condition.
     */
    public void interruptETOURConnection() {
        this.etourServerConnected = false;
        System.out.println("System: ETOUR server connection interrupted.");
    }

    /**
     * Restores the connection to the ETOUR server.
     * This is for testing purposes.
     */
    public void restoreETOURConnection() {
        this.etourServerConnected = true;
        System.out.println("System: ETOUR server connection restored.");
    }

    /**
     * Checks the ETOUR server connection status.
     *
     * @throws ETOURConnectionException if the server is not connected.
     */
    private void checkETOURConnection() throws ETOURConnectionException {
        if (!etourServerConnected) {
            throw new ETOURConnectionException("Connection to ETOUR server interrupted.");
        }
    }

    /**
     * Simulates a network delay to mimic real-world asynchronous operations.
     */
    private void simulateNetworkDelay() {
        try {
            TimeUnit.MILLISECONDS.sleep(500); // Simulate 500ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("System: Network delay simulation interrupted.");
        }
    }
}
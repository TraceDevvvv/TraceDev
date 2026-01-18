/*
Data Access Object (DAO) for CulturalObject.
Simulates database interaction for cultural objects, storing them in memory.
Includes a mechanism to simulate network connection interruption (ETOUR).
*/
package com.chatdev.modifybeneconaturale.dao;
import com.chatdev.modifybeneconaturale.model.CulturalObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class CulturalObjectDAO {
    // Simulates an in-memory database. Not thread-safe, but acceptable for single-user Swing app with UI blocking.
    private final Map<String, CulturalObject> culturalObjects = new HashMap<>();
    private Random random = new Random();
    private boolean simulateETOUR = false; // Flag to simulate connection interruption
    /**
     * Constructor initializes with some sample data.
     */
    public CulturalObjectDAO() {
        // Add some dummy data
        culturalObjects.put("CO001", new CulturalObject("CO001", "Mona Lisa", "Famous portrait painting by Leonardo da Vinci", "Louvre Museum, Paris", 860000000.00));
        culturalObjects.put("CO002", new CulturalObject("CO002", "Doge's Palace", "Historic building in Venice, Italy", "Venice, Italy", 120000000.00));
        culturalObjects.put("CO003", new CulturalObject("CO003", "Colosseum", "Ancient Roman amphitheatre", "Rome, Italy", 70000000.00));
        culturalObjects.put("CO004", new CulturalObject("CO004", "The Great Wave off Kanagawa", "Woodblock print by Hokusai", "Tokyo National Museum, Japan", 1500000.00));
        culturalObjects.put("CO005", new CulturalObject("CO005", "Terracotta Army", "Collection of terracotta sculptures depicting the armies of Qin Shi Huang", "Xi'an, China", 200000000.00));
    }
    /**
     * Retrieves all cultural objects from the simulated database.
     * Can simulate connection interruption.
     *
     * @return A list of all cultural objects.
     * @throws ConnectionInterruptionException If ETOUR simulation is active.
     */
    public List<CulturalObject> getAllCulturalObjects() throws ConnectionInterruptionException {
        // Introduce a small delay to simulate network latency, makes SwingWorker more noticeable
        try {
            Thread.sleep(500); // Simulate 0.5 second delay
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
        }
        // Simulate ETOUR with 30% probability for read operations
        if (simulateETOUR && random.nextDouble() < 0.3) {
            throw new ConnectionInterruptionException("Simulated connection interruption during data retrieval.");
        }
        return new ArrayList<>(culturalObjects.values());
    }
    /**
     * Retrieves a specific cultural object by its ID.
     *
     * @param id The ID of the cultural object to retrieve.
     * @return The CulturalObject if found, null otherwise.
     */
    public CulturalObject getCulturalObjectById(String id) {
        return culturalObjects.get(id);
    }
    /**
     * Updates an existing cultural object in the simulated database.
     * Can simulate connection interruption (ETOUR).
     *
     * @param updatedObject The CulturalObject with updated data.
     * @throws ConnectionInterruptionException If ETOUR simulation is active.
     * @throws CulturalObjectNotFoundException If the cultural object to update does not exist.
     * @throws IllegalArgumentException If the updated object provided is null.
     */
    public void updateCulturalObject(CulturalObject updatedObject) throws ConnectionInterruptionException, CulturalObjectNotFoundException {
        // Introduce a small delay to simulate network latency, makes SwingWorker more noticeable
        try {
            Thread.sleep(1000); // Simulate 1 second delay for update
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
        }
        // Simulate ETOUR with 50% probability when trying to update
        if (simulateETOUR && random.nextDouble() < 0.5) {
            throw new ConnectionInterruptionException("Simulated connection interruption during update operation.");
        }
        if (updatedObject == null) {
            throw new IllegalArgumentException("Updated cultural object cannot be null.");
        }
        if (!culturalObjects.containsKey(updatedObject.getId())) {
            throw new CulturalObjectNotFoundException("Cultural object with ID " + updatedObject.getId() + " not found.");
        }
        // Store a new copy to simulate database storage and prevent external modification of the stored object reference
        culturalObjects.put(updatedObject.getId(), updatedObject);
        System.out.println("DAO: Cultural object updated: " + updatedObject.getName());
    }
    /**
     * Sets the flag to simulate connection interruption.
     *
     * @param simulateETOUR True to simulate, false otherwise.
     */
    public void setSimulateETOUR(boolean simulateETOUR) {
        this.simulateETOUR = simulateETOUR;
    }
    /**
     * Custom exception for simulating connection interruption.
     */
    public static class ConnectionInterruptionException extends Exception {
        public ConnectionInterruptionException(String message) {
            super(message);
        }
    }
    /**
     * Custom exception for when a cultural object is not found in the DAO.
     */
    public static class CulturalObjectNotFoundException extends Exception {
        public CulturalObjectNotFoundException(String message) {
            super(message);
        }
    }
}
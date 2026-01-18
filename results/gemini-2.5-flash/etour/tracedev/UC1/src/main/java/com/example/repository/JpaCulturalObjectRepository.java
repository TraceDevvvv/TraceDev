package com.example.repository;

import com.example.domain.CulturalObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Concrete implementation of CulturalObjectRepository using a simulated JPA-like persistence.
 * This class corresponds to the 'JpaCulturalObjectRepository' class in the Class Diagram.
 * For demonstration purposes, it uses an in-memory map to simulate a database.
 */
public class JpaCulturalObjectRepository implements CulturalObjectRepository {

    // Simulating a database table with an in-memory map
    private final Map<String, CulturalObject> database = new ConcurrentHashMap<>();

    /**
     * Constructor for JpaCulturalObjectRepository.
     * Initializes with some dummy data for demonstration.
     */
    public JpaCulturalObjectRepository() {
        database.put("CO001", new CulturalObject("CO001", "Ancient Vase"));
        database.put("CO002", new CulturalObject("CO002", "Medieval Tapestry"));
        database.put("CO003", new CulturalObject("CO003", "Renaissance Painting"));
    }

    /**
     * Deletes a cultural object by its unique identifier.
     * Simulates database interaction.
     * @param id The ID of the cultural object to delete.
     * @return true if the object was successfully deleted, false otherwise.
     */
    @Override
    public boolean delete(String id) {
        System.out.println("[DB] Attempting to DELETE FROM CulturalObject WHERE id = " + id);
        // Simulate a potential ETOUR server interruption (R1.5.3) or a random failure
        if (Math.random() < 0.1) { // 10% chance of simulated failure
            System.err.println("[DB] Simulated ETOUR server interruption / database error for ID: " + id);
            return false;
        }

        // Simulate actual deletion
        CulturalObject removed = database.remove(id);
        if (removed != null) {
            System.out.println("[DB] Successfully deleted CulturalObject with ID: " + id);
            return true;
        } else {
            System.out.println("[DB] CulturalObject with ID: " + id + " not found for deletion.");
            return false;
        }
    }

    /**
     * Finds a cultural object by its unique identifier.
     * @param id The ID of the cultural object to find.
     * @return The CulturalObject if found, null otherwise.
     */
    @Override
    public CulturalObject findById(String id) {
        System.out.println("[DB] Searching for CulturalObject with ID: " + id);
        return database.get(id);
    }

    /**
     * Returns a list of all cultural objects.
     * @return A list of CulturalObject.
     */
    @Override
    public List<CulturalObject> findAll() {
        return new ArrayList<>(database.values());
    }
}
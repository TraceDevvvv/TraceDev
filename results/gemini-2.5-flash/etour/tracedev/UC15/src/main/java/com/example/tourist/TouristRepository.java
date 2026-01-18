package com.example.tourist;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Concrete implementation of ITouristRepository.
 * Manages connection & persistence to ETOUR DB (simulated in-memory).
 * Marked as DataAccess.
 */
public class TouristRepository implements ITouristRepository {
    // In-memory store to simulate a database. Key: Tourist ID, Value: Tourist object.
    private final Map<String, Tourist> touristStore = new HashMap<>();
    private boolean simulateConnectionError = false; // Flag to simulate DB connection issues

    public TouristRepository() {
        // Seed with some initial data for testing
        Tourist initialTourist = new Tourist(
                "T001",
                "Alice",
                "Smith",
                "alice.smith@example.com",
                "123 Main St, Anytown"
        );
        touristStore.put(initialTourist.getId(), initialTourist);
        // Another tourist
        Tourist anotherTourist = new Tourist(
                "T002",
                "Bob",
                "Johnson",
                "bob.johnson@example.com",
                "456 Oak Ave, Otherville"
        );
        touristStore.put(anotherTourist.getId(), anotherTourist);
    }

    /**
     * Sets a flag to simulate a connection error during save operations.
     * @param simulateConnectionError True to simulate an error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }

    @Override
    public Tourist findById(String id) {
        // Traceability for m6: SELECT * FROM Tourists WHERE id = touristId
        System.out.println("[TouristRepository] DB: SELECT * FROM Tourists WHERE id = " + id + " (simulated).");
        // Simulate database latency
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Tourist tourist = touristStore.get(id);
        // Traceability for m7: touristDataRow
        if (tourist != null) {
            System.out.println("[TouristRepository] Returned touristDataRow for ID: " + id);
        } else {
            System.out.println("[TouristRepository] No touristDataRow found for ID: " + id);
        }
        return tourist;
    }

    @Override
    public Tourist save(Tourist tourist) {
        if (simulateConnectionError) {
            System.err.println("[TouristRepository] Simulating connection error to ETOUR DB.");
            throw new RuntimeException("Connection to ETOUR interrupted. Please try again later."); // Simulate ETOUR server error
        }

        // Simulate database latency
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (tourist.getId() == null || tourist.getId().isEmpty()) {
            // Assign a new ID for new tourists
            String newId = "T-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
            Tourist newTourist = new Tourist(newId, tourist.getName(), tourist.getSurname(), tourist.getEmail(), tourist.getAddress());
            touristStore.put(newId, newTourist);
            System.out.println("[TouristRepository] New tourist saved with ID: " + newId);
            return newTourist;
        } else {
            // Update existing tourist
            touristStore.put(tourist.getId(), tourist);
            // Traceability for m30: UPDATE Tourists SET ... WHERE id = editedTouristDTO.id
            System.out.println("[TouristRepository] DB: UPDATE Tourists SET Name='" + tourist.getName() + "', Surname='" + tourist.getSurname() + "', Email='" + tourist.getEmail() + "', Address='" + tourist.getAddress() + "' WHERE id = " + tourist.getId() + " (simulated).");
            System.out.println("[TouristRepository] Tourist with ID: " + tourist.getId() + " updated.");
            return tourist;
        }
    }
}
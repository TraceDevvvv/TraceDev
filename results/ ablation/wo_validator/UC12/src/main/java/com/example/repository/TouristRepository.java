package com.example.repository;

import com.example.model.Tourist;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository class for Tourist data (simulated in-memory repository).
 */
public class TouristRepository {
    // Simulating a database with an in-memory list
    private List<Tourist> touristDatabase = new ArrayList<>();

    public TouristRepository() {
        // Initialize with some sample data for demonstration
        Tourist t1 = new Tourist();
        t1.setId(1);
        t1.setFirstName("John");
        t1.setLastName("Doe");
        t1.setEmail("john.doe@example.com");
        t1.setPhoneNumber("+1234567890");
        touristDatabase.add(t1);

        Tourist t2 = new Tourist();
        t2.setId(2);
        t2.setFirstName("Jane");
        t2.setLastName("Smith");
        t2.setEmail("jane.smith@example.com");
        t2.setPhoneNumber("+9876543210");
        touristDatabase.add(t2);
    }

    public List<Tourist> findAll() {
        return new ArrayList<>(touristDatabase);
    }

    public Optional<Tourist> findById(int id) {
        return touristDatabase.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    public List<Tourist> findByCriteria(String criteria) {
        // Simplified criteria search: matches if criteria is empty or name/email contains criteria (case-insensitive)
        if (criteria == null || criteria.trim().isEmpty()) {
            return findAll();
        }
        String lowerCriteria = criteria.toLowerCase();
        return touristDatabase.stream()
                .filter(t -> t.getFirstName().toLowerCase().contains(lowerCriteria) ||
                             t.getLastName().toLowerCase().contains(lowerCriteria) ||
                             t.getEmail().toLowerCase().contains(lowerCriteria))
                .collect(Collectors.toList());
    }

    public Tourist save(Tourist tourist) {
        // If tourist has no id, assign a new one (simulating auto-increment)
        if (tourist.getId() == 0) {
            int newId = touristDatabase.stream()
                    .mapToInt(Tourist::getId)
                    .max()
                    .orElse(0) + 1;
            tourist.setId(newId);
            touristDatabase.add(tourist);
        } else {
            // Update existing tourist
            touristDatabase.removeIf(t -> t.getId() == tourist.getId());
            touristDatabase.add(tourist);
        }
        return tourist;
    }

    /**
     * Simulates uploading tourist data to external server ETOUR.
     * @param touristId the tourist id
     * @param data the data map to upload
     * @return true if upload successful, false otherwise (connection interrupted)
     */
    public boolean updateTouristData(int touristId, Map<String, Object> data) {
        // Simulate server connection: 80% success rate
        // In reality, this would involve network call to ETOUR server.
        double random = Math.random();
        if (random < 0.8) {
            // Simulate successful connection
            // Here we would normally send data to server.
            return true;
        } else {
            // Simulate connection interrupted
            return false;
        }
    }
}
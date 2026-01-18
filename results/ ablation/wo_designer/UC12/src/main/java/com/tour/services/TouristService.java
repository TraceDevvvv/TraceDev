package com.tour.serv;

import com.tour.models.Tourist;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulates a service layer for Tourist operations.
 * In a real application, this would interact with a database.
 */
public class TouristService {
    // Mock data storage
    private List<Tourist> touristDatabase;

    public TouristService() {
        touristDatabase = new ArrayList<>();
        // Initialize with some sample tourists for demonstration
        touristDatabase.add(new Tourist("T001", "John Doe", "john@example.com", "+1234567890", "USA", "P1234567"));
        touristDatabase.add(new Tourist("T002", "Jane Smith", "jane@example.com", "+0987654321", "UK", "P7654321"));
        touristDatabase.add(new Tourist("T003", "Ali Khan", "ali@example.com", "+1122334455", "Pakistan", "P1122334"));
    }

    /**
     * Simulates searching tourists. Returns all tourists for simplicity.
     */
    public List<Tourist> searchTourists() {
        return new ArrayList<>(touristDatabase);
    }

    /**
     * Retrieves a Tourist by ID.
     * @param id the Tourist ID
     * @return the Tourist object, or null if not found.
     */
    public Tourist getTouristById(String id) {
        for (Tourist t : touristDatabase) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
}
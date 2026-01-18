package com.example.tourist;

import java.util.*;

public class TouristRepository {
    private Map<String, Tourist> touristMap = new HashMap<>();

    public TouristRepository() {
        // Initialize with some sample data
        touristMap.put("T001", new Tourist("T001", "Alice Johnson", "alice@example.com"));
        touristMap.put("T002", new Tourist("T002", "Bob Smith", "bob@example.com"));
        touristMap.put("T003", new Tourist("T003", "Charlie Brown", "charlie@example.com"));
    }

    public List<Tourist> searchTourists(String query) {
        // Simple search: if query is empty, return all tourists
        List<Tourist> result = new ArrayList<>();
        for (Tourist tourist : touristMap.values()) {
            if (query == null || query.isEmpty() ||
                tourist.getName().toLowerCase().contains(query.toLowerCase()) ||
                tourist.getEmail().toLowerCase().contains(query.toLowerCase()) ||
                tourist.getId().toLowerCase().contains(query.toLowerCase())) {
                result.add(tourist);
            }
        }
        return result;
    }

    public Tourist getTouristById(String id) {
        return touristMap.get(id);
    }

    public boolean deleteTourist(String id) {
        if (touristMap.containsKey(id)) {
            touristMap.remove(id);
            return true;
        }
        return false;
    }

    public List<Tourist> getAllTourists() {
        return new ArrayList<>(touristMap.values());
    }
}
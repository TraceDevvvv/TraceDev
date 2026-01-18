package com.example.datasource;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete database implementation of IDataSource.
 * Represents the ETOUR Database in the UML.
 */
public class ETourDatabase implements IDataSource {
    @Override
    public Map<String, Object> fetchSiteData(String id) {
        // Simulate database query
        // In a real app, this would be a SQL/NoSQL query
        Map<String, Object> result = new HashMap<>();
        
        // Mock data for demonstration
        if ("site123".equals(id)) {
            result.put("name", "Acropolis");
            result.put("description", "Ancient citadel in Athens.");
            result.put("location", "Athens, Greece");
            result.put("rating", 4.8);
        } else if ("site456".equals(id)) {
            result.put("name", "Colosseum");
            result.put("description", "Ancient amphitheater in Rome.");
            result.put("location", "Rome, Italy");
            result.put("rating", 4.9);
        } else {
            // Return empty map for unknown ID (simulates no data)
            return new HashMap<>();
        }
        
        return result;
    }
}
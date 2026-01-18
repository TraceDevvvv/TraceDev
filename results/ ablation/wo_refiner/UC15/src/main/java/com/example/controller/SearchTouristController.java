package com.example.controller;

import com.example.dto.TouristDTO;
import java.util.List;
import java.util.ArrayList;

/**
 * Controller for searching tourists.
 * Added to satisfy requirement REQ-006.
 */
public class SearchTouristController {
    
    /**
     * Activates the search tourist use case.
     * Added to satisfy requirement REQ-006.
     */
    public void activateSearchTourist() {
        System.out.println("Search tourist use case activated");
    }
    
    /**
     * Searches tourists based on criteria.
     * Added to satisfy requirement REQ-006.
     */
    public List<TouristDTO> searchTourist(String criteria) {
        System.out.println("Searching tourists with criteria: " + criteria);
        
        // Simulate database search
        List<TouristDTO> tourists = new ArrayList<>();
        tourists.add(new TouristDTO("tourist123", "John Doe", "john@example.com", "1234567890", "123 Main St"));
        tourists.add(new TouristDTO("tourist456", "Jane Smith", "jane@example.com", "0987654321", "456 Oak Ave"));
        tourists.add(new TouristDTO("tourist789", "Bob Johnson", "bob@example.com", "5551234567", "789 Pine Rd"));
        
        // Filter by criteria if provided
        if (criteria != null && !criteria.trim().isEmpty()) {
            String lowerCriteria = criteria.toLowerCase();
            tourists.removeIf(t -> 
                !t.getName().toLowerCase().contains(lowerCriteria) &&
                !t.getEmail().toLowerCase().contains(lowerCriteria) &&
                !t.getTouristId().toLowerCase().contains(lowerCriteria)
            );
        }
        
        return tourists;
    }
}
package com.example.adapter.out.persistence;

import com.example.application.port.out.CulturalObjectRepository;
import com.example.domain.CulturalObject;
import com.example.domain.SearchCriteria;
import com.example.exception.ConnectionException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Database repository implementation for cultural objects.
 * Simulates database access and query execution.
 */
public class DatabaseCulturalObjectRepository implements CulturalObjectRepository {
    // Simulated database of cultural objects
    private final List<CulturalObject> culturalObjects;
    
    public DatabaseCulturalObjectRepository() {
        this.culturalObjects = initializeSampleData();
    }
    
    private List<CulturalObject> initializeSampleData() {
        List<CulturalObject> objects = new ArrayList<>();
        objects.add(new CulturalObject("1", "Mona Lisa", "Oil painting", "Leonardo da Vinci", 
                new Date(1503 - 1900, 0, 1), "Painting"));
        objects.add(new CulturalObject("2", "The Starry Night", "Oil on canvas", "Vincent van Gogh", 
                new Date(1889 - 1900, 5, 1), "Painting"));
        objects.add(new CulturalObject("3", "David", "Marble statue", "Michelangelo", 
                new Date(1504 - 1900, 0, 1), "Sculpture"));
        objects.add(new CulturalObject("4", "The Persistence of Memory", "Oil on canvas", "Salvador Dali", 
                new Date(1931 - 1900, 0, 1), "Painting"));
        objects.add(new CulturalObject("5", "The Thinker", "Bronze sculpture", "Auguste Rodin", 
                new Date(1904 - 1900, 0, 1), "Sculpture"));
        return objects;
    }
    
    /**
     * Finds objects matching the search criteria.
     * Simulates database query with potential connection interruption.
     * @param criteria search criteria
     * @return matching cultural objects
     * @throws ConnectionException if connection to database fails
     */
    @Override
    public List<CulturalObject> findByCriteria(SearchCriteria criteria) throws ConnectionException {
        // Simulate potential connection interruption
        if (Math.random() < 0.1) { // 10% chance of simulated connection failure
            throw new ConnectionException("Connection to database server interrupted");
        }
        
        // Simulate database query processing time
        try {
            Thread.sleep(100 + (long)(Math.random() * 400)); // 100-500ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Filter objects based on criteria
        return culturalObjects.stream()
                .filter(obj -> matchesCriteria(obj, criteria))
                .collect(Collectors.toList());
    }
    
    /**
     * Helper method to check if an object matches the search criteria.
     */
    private boolean matchesCriteria(CulturalObject obj, SearchCriteria criteria) {
        // Check title
        if (criteria.getTitle() != null && !criteria.getTitle().trim().isEmpty()) {
            if (!obj.getTitle().toLowerCase().contains(criteria.getTitle().toLowerCase())) {
                return false;
            }
        }
        
        // Check creator
        if (criteria.getCreator() != null && !criteria.getCreator().trim().isEmpty()) {
            if (!obj.getCreator().toLowerCase().contains(criteria.getCreator().toLowerCase())) {
                return false;
            }
        }
        
        // Check object type
        if (criteria.getObjectType() != null && !criteria.getObjectType().trim().isEmpty()) {
            if (!obj.getType().equalsIgnoreCase(criteria.getObjectType())) {
                return false;
            }
        }
        
        // Check date range
        if (criteria.getDateRangeStart() != null && criteria.getDateRangeEnd() != null) {
            Date objDate = obj.getDate();
            if (objDate.before(criteria.getDateRangeStart()) || objDate.after(criteria.getDateRangeEnd())) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Simulates database query execution (internal method called from findByCriteria).
     */
    private void queryDatabase(SearchCriteria criteria) {
        // This method simulates the actual database query execution
        // In a real implementation, this would contain SQL/JPA query logic
        System.out.println("Executing database query for: " + 
                (criteria.getTitle() != null ? criteria.getTitle() : "all objects"));
    }
}
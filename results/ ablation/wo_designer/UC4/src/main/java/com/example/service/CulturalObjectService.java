package com.example.service;

import com.example.model.CulturalObject;
import com.example.model.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer that handles business logic for cultural object operations.
 * Simulates interaction with a data source (e.g., database, server).
 */
public class CulturalObjectService {

    // In-memory list to simulate a data source
    private List<CulturalObject> culturalObjectList;

    public CulturalObjectService() {
        initializeSampleData();
    }

    /**
     * Initializes the service with sample cultural objects.
     */
    private void initializeSampleData() {
        culturalObjectList = new ArrayList<>();
        culturalObjectList.add(new CulturalObject("1", "Mona Lisa", "Painting", "Paris", "Renaissance", "Famous portrait by Leonardo da Vinci."));
        culturalObjectList.add(new CulturalObject("2", "Statue of Liberty", "Sculpture", "New York", "Modern", "Gift from France to the USA."));
        culturalObjectList.add(new CulturalObject("3", "Colosseum", "Architecture", "Rome", "Ancient", "Ancient Roman amphitheater."));
        culturalObjectList.add(new CulturalObject("4", "The Thinker", "Sculpture", "Paris", "Modern", "Bronze sculpture by Auguste Rodin."));
        culturalObjectList.add(new CulturalObject("5", "Great Wall of China", "Architecture", "China", "Ancient", "Series of fortifications made of stone."));
        culturalObjectList.add(new CulturalObject("6", "The Starry Night", "Painting", "New York", "Modern", "Oil painting by Vincent van Gogh."));
    }

    /**
     * Searches for cultural objects based on the given criteria.
     * Matches are case-insensitive and partial matches are allowed.
     * @param criteria the search criteria
     * @return a list of cultural objects that match the criteria
     */
    public List<CulturalObject> searchCulturalObjects(SearchCriteria criteria) {
        if (criteria == null) {
            return new ArrayList<>(culturalObjectList); // Return all if no criteria
        }

        return culturalObjectList.stream()
                .filter(obj -> matchesCriteria(obj, criteria))
                .collect(Collectors.toList());
    }

    /**
     * Checks if a single cultural object matches the search criteria.
     * @param obj the cultural object to check
     * @param criteria the search criteria
     * @return true if the object matches all provided criteria, false otherwise
     */
    private boolean matchesCriteria(CulturalObject obj, SearchCriteria criteria) {
        if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            if (!obj.getName().toLowerCase().contains(criteria.getName().toLowerCase())) {
                return false;
            }
        }
        if (criteria.getType() != null && !criteria.getType().isEmpty()) {
            if (!obj.getType().equalsIgnoreCase(criteria.getType())) {
                return false;
            }
        }
        if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
            if (!obj.getLocation().toLowerCase().contains(criteria.getLocation().toLowerCase())) {
                return false;
            }
        }
        if (criteria.getEra() != null && !criteria.getEra().isEmpty()) {
            if (!obj.getEra().equalsIgnoreCase(criteria.getEra())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets all cultural objects (for testing purposes).
     * @return list of all cultural objects
     */
    public List<CulturalObject> getAllCulturalObjects() {
        return new ArrayList<>(culturalObjectList);
    }
}
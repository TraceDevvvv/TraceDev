package com.example.repository;

import com.example.model.CulturalObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing cultural objects.
 * Simulates a database storage and provides methods to check duplicates and save.
 */
public class CulturalObjectRepository {
    private List<CulturalObject> culturalObjects = new ArrayList<>();

    /**
     * Checks if a cultural object already exists (duplicate) based on equals method.
     * @param culturalObject the object to check
     * @return true if duplicate exists, false otherwise
     */
    public boolean isDuplicate(CulturalObject culturalObject) {
        return culturalObjects.contains(culturalObject);
    }

    /**
     * Saves the cultural object if not duplicate.
     * @param culturalObject the object to save
     * @return true if saved successfully, false if duplicate
     */
    public boolean save(CulturalObject culturalObject) {
        if (isDuplicate(culturalObject)) {
            return false;
        }
        culturalObjects.add(culturalObject);
        return true;
    }

    /**
     * Retrieves all cultural objects.
     * @return list of all cultural objects
     */
    public List<CulturalObject> getAll() {
        return new ArrayList<>(culturalObjects);
    }
}
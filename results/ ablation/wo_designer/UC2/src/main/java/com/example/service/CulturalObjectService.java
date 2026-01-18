package com.example.service;

import com.example.model.CulturalObject;
import com.example.repository.CulturalObjectRepository;

/**
 * Service layer for cultural object operations.
 * Handles validation, duplication checks, and interaction with repository.
 */
public class CulturalObjectService {
    private CulturalObjectRepository repository;

    public CulturalObjectService(CulturalObjectRepository repository) {
        this.repository = repository;
    }

    /**
     * Validates the cultural object data.
     * Basic validation: id, name, description, location should not be empty.
     * @param culturalObject the object to validate
     * @return true if valid, false otherwise
     */
    public boolean validateData(CulturalObject culturalObject) {
        if (culturalObject.getId() == null || culturalObject.getId().trim().isEmpty()) {
            return false;
        }
        if (culturalObject.getName() == null || culturalObject.getName().trim().isEmpty()) {
            return false;
        }
        if (culturalObject.getDescription() == null || culturalObject.getDescription().trim().isEmpty()) {
            return false;
        }
        if (culturalObject.getLocation() == null || culturalObject.getLocation().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Inserts a new cultural object after validation and duplication check.
     * @param culturalObject the object to insert
     * @return true if insertion successful, false otherwise
     */
    public boolean insertCulturalObject(CulturalObject culturalObject) {
        if (!validateData(culturalObject)) {
            // Corresponds to ErroreInserimentoDati use case
            System.out.println("Error: Invalid or insufficient data.");
            return false;
        }

        if (repository.isDuplicate(culturalObject)) {
            System.out.println("Error: Duplicate cultural object detected.");
            return false;
        }

        boolean saved = repository.save(culturalObject);
        if (saved) {
            System.out.println("Cultural object inserted successfully.");
            return true;
        } else {
            System.out.println("Error: Failed to save cultural object.");
            return false;
        }
    }
}
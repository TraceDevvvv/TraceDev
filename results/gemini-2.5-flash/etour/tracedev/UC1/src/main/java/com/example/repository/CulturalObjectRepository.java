package com.example.repository;

import com.example.domain.CulturalObject;

import java.util.List; // Added for displayCulturalObjects, though not directly used in delete sequence, good practice.

/**
 * Interface defining the contract for managing CulturalObject entities.
 * This corresponds to the 'CulturalObjectRepository' interface in the Class Diagram.
 * Note: Returns false on failure, potentially including external system issues like ETOUR server interruption (R1.5.3)
 */
public interface CulturalObjectRepository {
    /**
     * Deletes a cultural object by its unique identifier.
     * @param id The ID of the cultural object to delete.
     * @return true if the object was successfully deleted, false otherwise (e.g., object not found, database error, ETOUR server interruption).
     */
    boolean delete(String id);

    /**
     * Finds a cultural object by its unique identifier.
     * @param id The ID of the cultural object to find.
     * @return The CulturalObject if found, null otherwise.
     */
    CulturalObject findById(String id);

    // This method is implied by AgencyOperatorView.displayCulturalObjects but not part of delete sequence directly
    // Added for completeness as a repository might typically list objects.
    List<CulturalObject> findAll();
}
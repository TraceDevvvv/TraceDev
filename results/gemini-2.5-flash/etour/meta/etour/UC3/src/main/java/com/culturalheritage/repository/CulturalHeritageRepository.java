package com.culturalheritage.repository;

import com.culturalheritage.model.CulturalHeritageObject;
import java.util.List;
import java.util.Optional;

/**
 * Interface for cultural heritage object data access operations.
 * Defines the contract for interacting with a data store to perform CRUD operations
 * on CulturalHeritageObject entities.
 */
public interface CulturalHeritageRepository {

    /**
     * Finds a cultural heritage object by its unique identifier.
     *
     * @param id The unique identifier of the cultural heritage object.
     * @return An Optional containing the CulturalHeritageObject if found,
     *         or an empty Optional if no object with the given ID exists.
     */
    Optional<CulturalHeritageObject> findById(String id);

    /**
     * Saves a cultural heritage object to the data store.
     * If an object with the same ID already exists, it should be updated.
     * If not, a new object should be created.
     *
     * @param object The CulturalHeritageObject to be saved.
     */
    void save(CulturalHeritageObject object);

    /**
     * Retrieves all cultural heritage objects from the data store.
     *
     * @return A List of all CulturalHeritageObject entities.
     */
    List<CulturalHeritageObject> findAll();
}
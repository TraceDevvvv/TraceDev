package com.example.culturalgoods.repository;

import com.example.culturalgoods.model.CulturalGood;
import com.example.culturalgoods.exception.ConnectionException;

/**
 * Interface for managing persistence operations for CulturalGood entities.
 * Acts as a contract for data access.
 */
public interface ICulturalGoodRepository {

    /**
     * Saves a CulturalGood entity to the repository.
     * If the cultural good is new (no ID or not found by ID), it should be created.
     * If an existing cultural good with the same ID is passed, it should be updated.
     *
     * @param culturalGood The CulturalGood entity to save.
     * @return The saved CulturalGood entity, possibly with an updated ID or other generated fields.
     * @throws ConnectionException if there's an issue connecting to the underlying persistence layer.
     */
    CulturalGood save(CulturalGood culturalGood) throws ConnectionException;

    /**
     * Checks if a cultural good with the given name already exists in the repository.
     *
     * @param name The name to check for existence.
     * @return true if a cultural good with the given name exists, false otherwise.
     */
    boolean existsByName(String name);
}
package com.example.repository;

import com.example.domain.CulturalGood;
import java.util.List;

/**
 * Repository interface for CulturalGood entities.
 * Defines the contract for data access operations.
 */
public interface CulturalGoodRepository {
    /**
     * Find a CulturalGood by its ID.
     * @param id the unique identifier
     * @return the CulturalGood or null if not found
     */
    CulturalGood findById(String id);

    /**
     * Find a CulturalGood by its name.
     * @param name the name
     * @return the CulturalGood or null if not found
     */
    CulturalGood findByName(String name);

    /**
     * Save a CulturalGood to the data store.
     * @param culturalGood the entity to save
     */
    void save(CulturalGood culturalGood);

    /**
     * Check if a CulturalGood with the given name already exists.
     * @param name the name to check
     * @return true if exists, false otherwise
     */
    boolean existsByName(String name);

    /**
     * Retrieve all CulturalGoods.
     * @return a list of all CulturalGoods
     */
    List<CulturalGood> findAll();
}
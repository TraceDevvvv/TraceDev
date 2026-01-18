package com.example.infrastructure;

import com.example.domain.CulturalGoodAggregate;
import com.example.domain.Location;

/**
 * Repository interface for cultural goods.
 * The system will not accept duplicate cultural heritage entries.
 */
public interface CulturalGoodRepository {
    /**
     * Checks if a cultural good with the same name and location already exists.
     * @param name the name
     * @param location the location
     * @return true if a duplicate exists, false otherwise
     * @throws ConnectionException if connection to external server fails
     */
    boolean existsByNameAndLocation(String name, Location location) throws ConnectionException;
    
    /**
     * Saves a cultural good.
     * @param culturalGood the cultural good to save
     * @return the saved cultural good (with ID possibly filled)
     * @throws ConnectionException if connection to external server fails
     */
    CulturalGoodAggregate save(CulturalGoodAggregate culturalGood) throws ConnectionException;
}
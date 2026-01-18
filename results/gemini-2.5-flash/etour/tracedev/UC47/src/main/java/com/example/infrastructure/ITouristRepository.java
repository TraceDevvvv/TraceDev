package com.example.infrastructure;

import com.example.domain.TouristData;
import java.io.IOException; // For simulating connection errors

/**
 * Infrastructure Layer: Interface for accessing and persisting TouristData.
 * Defines the contract for data access operations, abstracting away implementation details.
 */
public interface ITouristRepository {

    /**
     * Finds a tourist by their unique identifier.
     * Corresponds to `findById(id: String)` in the class diagram.
     * @param id The ID of the tourist to find.
     * @return The TouristData object if found, null otherwise.
     * @throws IOException If a connection error occurs during data retrieval.
     */
    TouristData findById(String id) throws IOException;

    /**
     * Saves (creates or updates) a TouristData object.
     * Corresponds to `save(touristData: TouristData)` in the class diagram.
     * Stereotype <<EnsuresIntegrity>> implies that the repository should handle database-level integrity.
     * @param touristData The TouristData object to save.
     * @throws IOException If a connection error occurs during data persistence.
     */
    void save(TouristData touristData) throws IOException;
}
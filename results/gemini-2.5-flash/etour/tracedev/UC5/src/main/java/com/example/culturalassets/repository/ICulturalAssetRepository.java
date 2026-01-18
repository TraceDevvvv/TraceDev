package com.example.culturalassets.repository;

import com.example.culturalassets.domain.CulturalAsset;
import com.example.culturalassets.exception.ConnectionFailedException;

/**
 * Interface for data access operations related to CulturalAsset objects.
 * Defines the contract for fetching cultural assets from a data source.
 */
public interface ICulturalAssetRepository {
    /**
     * Finds a cultural asset by its unique identifier.
     * @param assetId The ID of the cultural asset to find.
     * @return The CulturalAsset object if found, or null if not found.
     * @throws ConnectionFailedException If there is an issue connecting to the data source.
     */
    CulturalAsset findById(String assetId) throws ConnectionFailedException;
}
package com.example.culturalassets.repository;

import com.example.culturalassets.domain.CulturalAsset;
import com.example.culturalassets.service.DatabaseConnection;
import com.example.culturalassets.exception.ConnectionFailedException;

/**
 * Concrete implementation of ICulturalAssetRepository.
 * Interacts with a DatabaseConnection to fetch cultural asset data.
 */
public class CulturalAssetRepositoryImpl implements ICulturalAssetRepository {
    private DatabaseConnection databaseConnection;

    /**
     * Constructs a CulturalAssetRepositoryImpl with a given DatabaseConnection.
     * @param databaseConnection The database connection to be used for data access.
     */
    public CulturalAssetRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Finds a cultural asset by its ID by querying the database.
     * @param assetId The unique identifier of the cultural asset.
     * @return The CulturalAsset object if found, otherwise null.
     * @throws ConnectionFailedException If there is an issue with the database connection during the query.
     */
    @Override
    public CulturalAsset findById(String assetId) throws ConnectionFailedException {
        System.out.println("Repository: Searching for asset with ID: " + assetId + " in the database.");
        // Ensure connection is established before querying. In a real app, this might be handled by a connection pool.
        try {
            // Simulate direct query, assuming databaseConnection manages its connection state
            // For robust error simulation, we'll try to connect implicitly or handle explicit connection here.
            // For now, let's assume queryAssetById handles the connection check.
            CulturalAsset culturalAsset = databaseConnection.queryAssetById(assetId);
            if (culturalAsset == null) {
                System.out.println("Repository: Asset with ID " + assetId + " not found.");
            } else {
                System.out.println("Repository: Found cultural asset: " + culturalAsset.getName());
            }
            return culturalAsset;
        } catch (ConnectionFailedException e) {
            // Re-throw the exception to indicate a connection problem to the caller (UseCase)
            System.err.println("Repository: Connection to ETOUR server interrupted: " + e.getMessage());
            throw e;
        }
    }
}
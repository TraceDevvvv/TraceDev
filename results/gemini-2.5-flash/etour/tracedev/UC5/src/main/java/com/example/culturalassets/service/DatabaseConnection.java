package com.example.culturalassets.service;

import com.example.culturalassets.domain.CulturalAsset;
import com.example.culturalassets.exception.ConnectionFailedException;
import java.util.HashMap;
import java.util.Map;

/**
 * Simulates a database connection for cultural assets.
 * This class handles the actual data storage and retrieval simulation.
 */
public class DatabaseConnection {
    private String connectionString;
    private boolean isConnected;

    // A dummy in-memory store for demonstration purposes
    private Map<String, CulturalAsset> assetStore;

    /**
     * Constructs a DatabaseConnection with a specified connection string.
     * Initializes a dummy data store.
     * @param connectionString The string used to identify the database.
     */
    public DatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
        this.isConnected = false;
        this.assetStore = new HashMap<>();
        // Populate with some dummy data
        assetStore.put("A001", new CulturalAsset("A001", "Mona Lisa", "A half-length portrait painting by Italian artist Leonardo da Vinci.", "Louvre Museum, Paris", "Painting"));
        assetStore.put("A002", new CulturalAsset("A002", "Colosseum", "An oval amphitheatre in the centre of the city of Rome, Italy.", "Rome, Italy", "Historic Site"));
        assetStore.put("A003", new CulturalAsset("A003", "Statue of David", "A masterpiece of Renaissance sculpture created in marble by the Italian artist Michelangelo.", "Galleria dell'Accademia, Florence", "Sculpture"));
    }

    /**
     * Simulates connecting to the database.
     */
    public void connect() {
        System.out.println("DB: Attempting to connect to " + connectionString + "...");
        // Simulate potential connection failure for testing
        if (connectionString.contains("fail")) {
            System.out.println("DB: Connection failed due to simulated error.");
            this.isConnected = false;
            // No exception here, it's caught at the repository level during query
        } else {
            this.isConnected = true;
            System.out.println("DB: Successfully connected to " + connectionString + ".");
        }
    }

    /**
     * Simulates disconnecting from the database.
     */
    public void disconnect() {
        if (isConnected) {
            System.out.println("DB: Disconnecting from " + connectionString + ".");
            isConnected = false;
        } else {
            System.out.println("DB: Already disconnected or never connected.");
        }
    }

    /**
     * Simulates querying for an asset by its ID.
     * @param assetId The ID of the asset to query.
     * @return The CulturalAsset data if found, or null.
     * @throws ConnectionFailedException If the database is not connected.
     */
    public CulturalAsset queryAssetById(String assetId) throws ConnectionFailedException {
        if (!isConnected) {
            throw new ConnectionFailedException("Database is not connected. Cannot query asset.");
        }
        System.out.println("DB: Querying for asset with ID: " + assetId);
        CulturalAsset asset = assetStore.get(assetId);
        if (asset == null) {
            System.out.println("DB: Asset with ID " + assetId + " not found.");
        } else {
            System.out.println("DB: Found asset '" + asset.getName() + "'.");
        }
        return asset;
    }
}
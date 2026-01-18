package com.example.repository;

import com.example.model.CulturalHeritage;
import com.example.exception.ETOURConnectionException;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of CulturalHeritageRepository.
 * Simulates database/ETOUR server interactions.
 */
public class CulturalHeritageRepositoryImpl implements CulturalHeritageRepository {
    // In-memory simulation of a database
    private Map<Integer, CulturalHeritage> culturalHeritageMap;
    private boolean simulateConnectionFailure = false;

    public CulturalHeritageRepositoryImpl() {
        culturalHeritageMap = new HashMap<>();
        // Populate with dummy data
        culturalHeritageMap.put(1, new CulturalHeritage(1, "Ancient Temple", "A historic temple."));
        culturalHeritageMap.put(2, new CulturalHeritage(2, "Medieval Castle", "A castle from medieval times."));
    }

    @Override
    public CulturalHeritage findById(int id) {
        return culturalHeritageMap.get(id);
    }

    @Override
    public boolean delete(int id) {
        if (culturalHeritageMap.containsKey(id)) {
            culturalHeritageMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkConnection() throws ETOURConnectionException {
        // Simulate connection test
        if (simulateConnectionFailure) {
            throw new ETOURConnectionException(503, "ETOUR Server unavailable");
        }
        // Assume connection is always successful unless simulated failure
        return true;
    }

    public void setSimulateConnectionFailure(boolean failure) {
        this.simulateConnectionFailure = failure;
    }

    @Override
    public CulturalHeritage getCulturalHeritage(int id) {
        // This method retrieves a CulturalHeritage object.
        return findById(id);
    }

    @Override
    public void queryCulturalHeritage(int id) {
        // This method corresponds to the "Query cultural heritage" message.
        // In a real scenario, this would initiate a query to the database.
        // For traceability, we just call findById.
        findById(id);
    }

    @Override
    public void deleteCulturalHeritage(int id) {
        // This method corresponds to the "Delete cultural heritage" message.
        delete(id);
    }

    @Override
    public CulturalHeritage queryCulturalHeritageData(int id) {
        // This method corresponds to the return message "CulturalHeritage data".
        return findById(id);
    }
}
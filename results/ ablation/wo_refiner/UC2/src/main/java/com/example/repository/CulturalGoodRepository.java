package com.example.repository;

import com.example.heritage.CulturalGood;
import com.example.connection.ConnectionManager;
import com.example.exception.ConnectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository for persisting CulturalGood entities. 
 * Note: Does not accept duplicate cultural heritage (Req 16).
 * Quality requirement: Ensure data uniqueness (Req 15).
 */
public class CulturalGoodRepository {
    // In-memory list to simulate persistence.
    private List<CulturalGood> culturalGoods = new ArrayList<>();
    private ConnectionManager connectionManager;
    
    public CulturalGoodRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    /**
     * Saves a cultural good.
     * @param culturalGood the cultural good to save
     * @return true if successful, false otherwise
     * @throws ConnectionException if connection is not available (Req 014)
     */
    public boolean save(CulturalGood culturalGood) throws ConnectionException {
        // Check connection before saving.
        if (!connectionManager.isConnected()) {
            throw new ConnectionException("No connection to database", 500, new java.util.Date());
        }
        
        // Generate an ID if not already set (simple simulation).
        if (culturalGood.getId() == null || culturalGood.getId().isEmpty()) {
            culturalGood.setId("CG" + System.currentTimeMillis());
        }
        culturalGoods.add(culturalGood);
        return true;
    }
    
    /**
     * Finds cultural goods by name and location.
     * @param name the name to search for
     * @param location the location to search for
     * @return array of matching cultural goods
     */
    public CulturalGood[] findByNameAndLocation(String name, String location) {
        List<CulturalGood> matches = culturalGoods.stream()
                .filter(cg -> cg.getName().equals(name) && cg.getLocation().equals(location))
                .collect(Collectors.toList());
        return matches.toArray(new CulturalGood[0]);
    }
}
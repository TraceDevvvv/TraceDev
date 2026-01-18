package com.system.repository;

import com.system.domain.CulturalGood;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * In-memory implementation of CulturalGood repository.
 * For simplicity, uses a map as data source.
 */
public class CulturalGoodRepository implements ICulturalGoodRepository {
    // Simulated data source
    private DataSource dataSource;
    private Map<String, CulturalGood> memoryDataSource = new HashMap<>();

    public CulturalGoodRepository() {
        // Prepopulate with a sample cultural good for testing
        java.util.Date now = new java.util.Date();
        memoryDataSource.put("CG001", new CulturalGood("CG001", "Ancient Vase", "A Greek amphora", "Archaeology", "Gallery 1", now));
    }

    @Override
    public CulturalGood findById(String id) {
        // Simulate database lookup
        return memoryDataSource.get(id);
    }

    @Override
    public CulturalGood save(CulturalGood culturalGood) {
        // Simulate save/update operation
        memoryDataSource.put(culturalGood.getId(), culturalGood);
        return culturalGood;
    }
}
package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * Simple in-memory implementation of TeachingRepository.
 * Uses a Map as data source for demonstration.
 */
public class TeachingRepositoryImpl implements TeachingRepository {
    private Map<String, Teaching> dataStore;
    private DataSource dataSource;

    public TeachingRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.dataStore = new HashMap<>();
    }

    @Override
    public Teaching findById(String id) {
        return dataStore.get(id);
    }

    @Override
    public Teaching save(Teaching teaching) {
        dataStore.put(teaching.getId(), teaching);
        return teaching;
    }

    @Override
    public void delete(String id) {
        dataStore.remove(id);
    }

    @Override
    public void deleteAtomic(String id) {
        // Atomic deletion: remove in one step, no intermediate states.
        // This is a simple implementation; in a real system, this would use transactions.
        dataStore.remove(id);
    }

    @Override
    public List<Teaching> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    /**
     * Perform atomic delete (ensure referential integrity) as per sequence diagram.
     * This method corresponds to the sequence diagram message "perform atomic delete (ensure referential integrity)".
     */
    public void performAtomicDelete(String id) {
        // In a real implementation, perform atomic delete with referential integrity checks.
        // For now, we call deleteAtomic.
        deleteAtomic(id);
    }
}
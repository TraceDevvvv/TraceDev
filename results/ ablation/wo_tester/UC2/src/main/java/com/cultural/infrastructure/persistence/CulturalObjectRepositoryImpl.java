package com.cultural.infrastructure.persistence;

import com.cultural.application.port.out.CulturalObjectRepository;
import com.cultural.domain.model.CulturalObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of CulturalObjectRepository.
 * Uses a simple map as data source for demonstration.
 */
public class CulturalObjectRepositoryImpl implements CulturalObjectRepository {
    private Map<String, CulturalObject> dataSource; // Simulated data source
    private static int idCounter = 1;

    public CulturalObjectRepositoryImpl() {
        this.dataSource = new ConcurrentHashMap<>();
    }

    @Override
    public CulturalObject save(CulturalObject culturalObject) {
        // Generate ID if not already set (e.g., temp ID)
        String id = culturalObject.getId();
        if (id == null || id.startsWith("temp-")) {
            id = generateId();
            culturalObject = new CulturalObject(id,
                    culturalObject.getName(),
                    culturalObject.getDescription(),
                    culturalObject.getType(),
                    culturalObject.getLocation());
        }
        dataSource.put(id, culturalObject);
        return culturalObject;
    }

    @Override
    public CulturalObject findById(String id) {
        return dataSource.get(id);
    }

    @Override
    public List<CulturalObject> findByNameAndLocation(String name, String location) {
        List<CulturalObject> result = new ArrayList<>();
        for (CulturalObject co : dataSource.values()) {
            if (co.getName().equals(name) && co.getLocation().equals(location)) {
                result.add(co);
            }
        }
        return result;
    }

    @Override
    public List<CulturalObject> findAll() {
        return new ArrayList<>(dataSource.values());
    }

    private String generateId() {
        return "CO-" + (idCounter++);
    }
}
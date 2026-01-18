package com.example.convention;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple in-memory implementation of ConventionRepository.
 * For demonstration purposes only.
 */
public class ConventionRepositoryImpl implements ConventionRepository {
    private Map<String, Convention> storageByPointId = new HashMap<>();

    @Override
    public Convention findByRefreshmentPointId(String pointId) {
        return storageByPointId.get(pointId);
    }

    @Override
    public Convention save(Convention convention) {
        storageByPointId.put(convention.getRefreshmentPointId(), convention);
        return convention;
    }
}
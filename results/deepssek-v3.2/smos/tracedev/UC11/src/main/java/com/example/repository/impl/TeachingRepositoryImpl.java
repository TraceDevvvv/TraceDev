package com.example.repository.impl;

import com.example.entity.Teaching;
import com.example.repository.TeachingRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of TeachingRepository.
 */
public class TeachingRepositoryImpl implements TeachingRepository {
    private Map<String, Teaching> store = new HashMap<>();

    public TeachingRepositoryImpl() {
        // Pre-populate with sample data
        store.put("TE001", new Teaching("TE001", "MATH101", "Calculus"));
        store.put("TE002", new Teaching("TE002", "PHY101", "Mechanics"));
        store.put("TE003", new Teaching("TE003", "CHEM101", "Organic Chemistry"));
    }

    @Override
    public Teaching findById(String id) {
        return store.get(id);
    }
}
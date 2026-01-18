package com.example.repository.impl;

import com.example.entity.Class;
import com.example.repository.ClassRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of ClassRepository.
 */
public class ClassRepositoryImpl implements ClassRepository {
    private Map<String, Class> store = new HashMap<>();

    public ClassRepositoryImpl() {
        // Pre-populate with sample data
        store.put("C001", new Class("C001", "Mathematics 101", "2023-2024"));
        store.put("C002", new Class("C002", "Physics 101", "2023-2024"));
        store.put("C003", new Class("C003", "Chemistry 101", "2022-2023"));
    }

    @Override
    public Class findById(String id) {
        return store.get(id);
    }

    @Override
    public List<Class> findByAcademicYear(String year) {
        List<Class> result = new ArrayList<>();
        for (Class c : store.values()) {
            if (year.equals(c.getAcademicYear())) {
                result.add(c);
            }
        }
        return result;
    }
}
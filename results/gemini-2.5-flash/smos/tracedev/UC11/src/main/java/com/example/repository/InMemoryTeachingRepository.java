package com.example.repository;

import com.example.model.Teaching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of TeachingRepository for demonstration purposes.
 * Stores Teaching objects in a HashMap.
 */
public class InMemoryTeachingRepository implements TeachingRepository {
    private final Map<String, Teaching> teachings = new HashMap<>();

    public InMemoryTeachingRepository() {
        // Seed with some initial data
        teachings.put("T1", new Teaching("T1", "Algebra Fundamentals", "C1"));
        teachings.put("T2", new Teaching("T2", "Geometry Basics", "C1"));
        teachings.put("T3", new Teaching("T3", "Classical Mechanics", "C2"));
        teachings.put("T4", new Teaching("T4", "Organic Chemistry", "C3"));
    }

    @Override
    public List<Teaching> findBySchoolClassId(String schoolClassId) {
        System.out.println("[TeachingRepo] Finding Teachings for Class ID: " + schoolClassId);
        return teachings.values().stream()
                .filter(t -> t.getSchoolClassId().equals(schoolClassId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Teaching> findByClassId(String classId) {
        // Delegates to findBySchoolClassId, assuming 'classId' in diagram refers to 'schoolClassId' in code.
        return findBySchoolClassId(classId);
    }

    @Override
    public Teaching findById(String id) {
        return teachings.get(id);
    }
}
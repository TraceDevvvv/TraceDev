package com.example.repository;

import com.example.model.SchoolClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of SchoolClassRepository for demonstration purposes.
 * Stores SchoolClass objects in a HashMap.
 */
public class InMemorySchoolClassRepository implements SchoolClassRepository {
    private final Map<String, SchoolClass> schoolClasses = new HashMap<>();

    public InMemorySchoolClassRepository() {
        // Seed with some initial data
        schoolClasses.put("C1", new SchoolClass("C1", "Mathematics 101", "Y1"));
        schoolClasses.put("C2", new SchoolClass("C2", "Physics 201", "Y1"));
        schoolClasses.put("C3", new SchoolClass("C3", "Chemistry 101", "Y2"));
    }

    @Override
    public List<SchoolClass> findByAcademicYearId(String yearId) {
        System.out.println("[SchoolClassRepo] Finding Classes for Academic Year ID: " + yearId);
        return schoolClasses.values().stream()
                .filter(c -> c.getAcademicYearId().equals(yearId))
                .collect(Collectors.toList());
    }

    @Override
    public SchoolClass findById(String id) {
        return schoolClasses.get(id);
    }
}
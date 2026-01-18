package com.example.repository;

import com.example.model.AcademicYear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of AcademicYearRepository for demonstration purposes.
 * Stores AcademicYear objects in a HashMap.
 */
public class InMemoryAcademicYearRepository implements AcademicYearRepository {
    private final Map<String, AcademicYear> academicYears = new HashMap<>();

    public InMemoryAcademicYearRepository() {
        // Seed with some initial data
        academicYears.put("Y1", new AcademicYear("Y1", 2023, "2023-2024 Academic Year"));
        academicYears.put("Y2", new AcademicYear("Y2", 2024, "2024-2025 Academic Year"));
    }

    @Override
    public List<AcademicYear> findAll() {
        System.out.println("[AcademicYearRepo] Finding all Academic Years.");
        return new ArrayList<>(academicYears.values());
    }

    @Override
    public AcademicYear findById(String id) {
        return academicYears.get(id);
    }
}
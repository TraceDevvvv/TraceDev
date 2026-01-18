package com.example.reportcard.repository;

import com.example.reportcard.domain.Class;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Mock implementation of IClassRepository for demonstration purposes.
 * Simulates an in-memory database.
 */
public class ClassRepositoryImpl implements IClassRepository {
    private Map<String, Class> classes = new HashMap<>();

    public ClassRepositoryImpl() {
        // Seed data
        classes.put("C101", new Class("C101", "Math Grade 10", "AY2023", "P001"));
        classes.put("C102", new Class("C102", "Physics Grade 10", "AY2023", "P001"));
        classes.put("C103", new Class("C103", "Chemistry Grade 11", "AY2024", "P001"));
        classes.put("C201", new Class("C201", "Literature Grade 10", "AY2023", "P002"));
    }

    @Override
    public List<Class> findClassesByAcademicYearAndProfessor(String academicYearId, String professorId) {
        return classes.values().stream()
                .filter(c -> c.getAcademicYearId().equals(academicYearId) && c.getProfessorId().equals(professorId))
                .collect(Collectors.toList());
    }

    @Override
    public Class findById(String id) {
        return classes.get(id);
    }

    /**
     * Helper method for mock data to be used by other repositories (e.g., AcademicYearRepository).
     * In a real application, this would not be public or would be part of a more complex data access strategy.
     * @return All classes in the mock repository.
     */
    public List<Class> findAllClasses() {
        return new ArrayList<>(classes.values());
    }
}
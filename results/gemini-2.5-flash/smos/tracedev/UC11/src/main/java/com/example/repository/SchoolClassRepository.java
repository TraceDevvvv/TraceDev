package com.example.repository;

import com.example.model.SchoolClass;

import java.util.List;

/**
 * Interface for data access operations related to SchoolClass entities.
 * Renamed from ClassRepository to SchoolClassRepository to avoid Java keyword conflict.
 */
public interface SchoolClassRepository {
    /**
     * Finds all classes for a given academic year ID.
     * @param yearId The ID of the academic year.
     * @return A list of SchoolClass objects.
     */
    List<SchoolClass> findByAcademicYearId(String yearId);

    /**
     * Finds a class by its ID.
     * @param id The ID of the class.
     * @return The SchoolClass object if found, null otherwise.
     */
    SchoolClass findById(String id);
}
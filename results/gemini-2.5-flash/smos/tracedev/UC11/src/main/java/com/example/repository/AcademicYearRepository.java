package com.example.repository;

import com.example.model.AcademicYear;

import java.util.List;

/**
 * Interface for data access operations related to AcademicYear entities.
 */
public interface AcademicYearRepository {
    /**
     * Finds all academic years.
     * @return A list of all academic years.
     */
    List<AcademicYear> findAll();

    /**
     * Finds an academic year by its ID.
     * @param id The ID of the academic year.
     * @return The AcademicYear object if found, null otherwise.
     */
    AcademicYear findById(String id);
}
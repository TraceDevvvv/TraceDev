package com.example.reportcard.repository;

import com.example.reportcard.domain.AcademicYear;

import java.util.List;

/**
 * Interface for Data Access operations related to AcademicYear entities.
 */
public interface IAcademicYearRepository {
    /**
     * Finds all academic years associated with a specific professor.
     * @param professorId The ID of the professor.
     * @return A list of AcademicYear entities.
     */
    List<AcademicYear> findYearsByProfessorId(String professorId);

    /**
     * Finds an academic year by its ID.
     * @param id The ID of the academic year.
     * @return The AcademicYear entity, or null if not found.
     */
    AcademicYear findById(String id);
}
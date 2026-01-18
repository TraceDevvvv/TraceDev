package com.example.reportcard.repository;

import com.example.reportcard.domain.Class;

import java.util.List;

/**
 * Interface for Data Access operations related to Class entities.
 */
public interface IClassRepository {
    /**
     * Finds all classes for a specific academic year and taught by a specific professor.
     * @param academicYearId The ID of the academic year.
     * @param professorId The ID of the professor.
     * @return A list of Class entities.
     */
    List<Class> findClassesByAcademicYearAndProfessor(String academicYearId, String professorId);

    /**
     * Finds a class by its ID.
     * @param id The ID of the class.
     * @return The Class entity, or null if not found.
     */
    Class findById(String id);
}
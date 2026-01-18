package com.example;

import java.util.List;

/**
 * Interface for academic data operations.
 * Defines methods to retrieve academic years and classes related to a professor.
 */
public interface IAcademicRepository {

    /**
     * Finds academic years associated with a given professor.
     *
     * @param professorId The ID of the professor.
     * @return A list of AcademicYear objects.
     * @throws DataAccessException if there is an issue accessing the data source.
     */
    List<AcademicYear> findAcademicYearsByProfessor(String professorId) throws DataAccessException;

    /**
     * Finds classes for a specific academic year and professor.
     *
     * @param professorId The ID of the professor.
     * @param academicYearId The ID of the academic year.
     * @return A list of Class objects.
     * @throws DataAccessException if there is an issue accessing the data source.
     */
    List<Class> findClassesByAcademicYearAndProfessor(String professorId, String academicYearId) throws DataAccessException;
}
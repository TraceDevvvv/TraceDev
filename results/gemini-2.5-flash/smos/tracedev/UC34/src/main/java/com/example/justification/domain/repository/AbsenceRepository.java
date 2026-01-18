package com.example.justification.domain.repository;

import com.example.justification.domain.model.Absence;
import java.util.List;

/**
 * Interface for data access operations related to Absences.
 * Follows the Repository pattern.
 */
public interface AbsenceRepository {
    /**
     * Finds all absences for a given student within a specific year.
     *
     * @param studentId The ID of the student.
     * @param year The academic year to search within.
     * @return A list of Absence objects.
     */
    List<Absence> findByStudentIdAndYear(String studentId, int year);
}
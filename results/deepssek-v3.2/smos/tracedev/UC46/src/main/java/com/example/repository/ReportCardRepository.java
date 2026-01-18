package com.example.repository;

import com.example.entity.ReportCardEntity;

/**
 * Repository interface for Report Card entities.
 */
public interface ReportCardRepository {
    /**
     * Saves a report card entity.
     * @param reportCard the entity to save
     * @return the saved entity
     */
    ReportCardEntity save(ReportCardEntity reportCard);

    /**
     * Checks if a report card already exists for a student in a given academic year.
     * @param studentId the student id
     * @param year the academic year
     * @return true if a report card exists, false otherwise
     */
    boolean existsByStudentAndAcademicYear(int studentId, int year);
}
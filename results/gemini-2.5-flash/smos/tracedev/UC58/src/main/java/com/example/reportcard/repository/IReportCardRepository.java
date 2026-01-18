package com.example.reportcard.repository;

import com.example.reportcard.domain.ReportCard;

/**
 * Interface for Data Access operations related to ReportCard entities.
 */
public interface IReportCardRepository {
    /**
     * Finds a report card for a specific student, academic year, and quarter.
     * @param studentId The ID of the student.
     * @param academicYearId The ID of the academic year.
     * @param quarter The quarter (e.g., "Q1", "Q2").
     * @return The ReportCard entity, or null if not found.
     */
    ReportCard findByStudentYearAndQuarter(String studentId, String academicYearId, String quarter);
}
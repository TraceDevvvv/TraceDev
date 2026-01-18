package com.example.reportcard.dataaccess;

import com.example.reportcard.domain.ReportCard;
import com.example.reportcard.domain.Month;
import java.util.List;

/**
 * Interface for data access operations related to ReportCard.
 */
public interface IReportCardRepository {
    /**
     * Finds a report card for a specific student and academic period.
     * @param studentId The ID of the student.
     * @param academicYearId The ID of the academic year.
     * @param months The list of months covered by the report.
     * @return A ReportCard object, or null if not found.
     * @throws RuntimeException if there's a data access error (e.g., SMOS connection failure).
     */
    ReportCard findByStudentAndPeriod(String studentId, String academicYearId, List<Month> months);
}
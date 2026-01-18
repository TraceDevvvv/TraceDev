package com.example.repositories;

import com.example.domain.Report;
import java.util.List;

/**
 * Repository interface for report data access.
 */
public interface ReportRepository {
    List<Report> getStudentReports(String studentId);
    List<Report> fetchStudentReports(String studentId);
    Report getReportById(String reportId);
    Report fetchReportById(String reportId);
}
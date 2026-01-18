package com.schoolsystem.repository;

import com.schoolsystem.domain.ReportCard;
import java.util.List;

/**
 * Repository interface for ReportCard data access.
 */
public interface IReportCardRepository {
    List<ReportCard> findAllByStudentId(String studentId);
    ReportCard findById(String reportId);
}
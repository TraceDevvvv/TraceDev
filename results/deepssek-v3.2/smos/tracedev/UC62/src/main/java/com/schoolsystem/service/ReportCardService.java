package com.schoolsystem.service;

import com.schoolsystem.domain.ReportCard;
import com.schoolsystem.repository.IReportCardRepository;
import java.util.List;

/**
 * Service layer for ReportCard business logic.
 */
public class ReportCardService {
    private IReportCardRepository reportCardRepository;

    public ReportCardService(IReportCardRepository reportCardRepository) {
        this.reportCardRepository = reportCardRepository;
    }

    public List<ReportCard> getReportCardsForStudent(String studentId) {
        return reportCardRepository.findAllByStudentId(studentId);
    }

    public ReportCard getReportCardDetails(String reportId) {
        return reportCardRepository.findById(reportId);
    }
}
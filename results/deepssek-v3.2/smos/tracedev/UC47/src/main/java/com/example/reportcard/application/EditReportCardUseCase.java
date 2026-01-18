package com.example.reportcard.application;

import com.example.reportcard.domain.ReportCardDTO;

/**
 * Use case interface for editing a report card.
 */
public interface EditReportCardUseCase {
    ReportCardDTO loadReportCard(String studentId);
    boolean modifyReportCard(ReportCardDTO modifiedData);
}
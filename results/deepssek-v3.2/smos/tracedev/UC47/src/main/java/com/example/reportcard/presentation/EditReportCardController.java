package com.example.reportcard.presentation;

import com.example.reportcard.domain.ReportCardDTO;

/**
 * Controller interface for handling edit report card requests.
 */
public interface EditReportCardController {
    void handleEditRequest(String studentId);
    void handleSaveRequest(ReportCardDTO reportCardData);
}
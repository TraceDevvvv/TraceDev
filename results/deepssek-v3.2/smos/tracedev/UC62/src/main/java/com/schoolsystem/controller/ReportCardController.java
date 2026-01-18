package com.schoolsystem.controller;

import com.schoolsystem.domain.ReportCard;
import com.schoolsystem.service.ReportCardService;
import com.schoolsystem.authentication.AuthenticationService;
import com.schoolsystem.view.ReportCardView;
import java.util.List;

/**
 * Controller that handles report card requests from the view.
 */
public class ReportCardController {
    private ReportCardService reportCardService;
    private AuthenticationService authenticationService;
    private ReportCardView view; // The view to update

    public ReportCardController(ReportCardService reportCardService,
                                AuthenticationService authenticationService,
                                ReportCardView view) {
        this.reportCardService = reportCardService;
        this.authenticationService = authenticationService;
        this.view = view;
    }

    /**
     * Fetches and displays list of report cards for a student.
     * Sequence diagram step: showReportCardList
     */
    public void showReportCardList(String studentId, String sessionId) {
        try {
            if (!authenticationService.validateSession(sessionId)) {
                view.showErrorMessage("Invalid session. Please log in.");
                return;
            }
            List<ReportCard> reportCards = reportCardService.getReportCardsForStudent(studentId);
            view.displayReportCardList(reportCards);
        } catch (Exception e) {
            view.showErrorMessage("Error fetching report cards: " + e.getMessage());
        }
    }

    /**
     * Fetches and displays details of a specific report card.
     * Sequence diagram step: showReportCardDetails
     */
    public void showReportCardDetails(String reportId, String sessionId) {
        try {
            if (!authenticationService.validateSession(sessionId)) {
                view.showErrorMessage("Invalid session. Please log in.");
                return;
            }
            ReportCard reportCard = reportCardService.getReportCardDetails(reportId);
            view.displayReportCardDetails(reportCard);
        } catch (Exception e) {
            view.showErrorMessage("Error fetching report details: " + e.getMessage());
        }
    }
}
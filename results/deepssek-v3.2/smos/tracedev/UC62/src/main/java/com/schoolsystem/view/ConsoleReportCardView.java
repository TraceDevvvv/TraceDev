package com.schoolsystem.view;

import com.schoolsystem.controller.ReportCardController;
import com.schoolsystem.domain.ReportCard;
import java.util.List;

/**
 * Console-based implementation of ReportCardView.
 */
public class ConsoleReportCardView extends ReportCardView {
    private ReportCardController controller; // To call controller methods

    public void setController(ReportCardController controller) {
        this.controller = controller;
    }

    @Override
    public void displayReportCardList(List<ReportCard> reportCards) {
        System.out.println("=== Report Card List ===");
        for (ReportCard rc : reportCards) {
            System.out.println(" - " + rc.getReportId() + ": " + rc.getTerm() + " " + rc.getYear());
        }
        System.out.println("=== End of List ===");
    }

    @Override
    public void displayReportCardDetails(ReportCard reportCard) {
        System.out.println("=== Report Card Details ===");
        System.out.println("Report ID: " + reportCard.getReportId());
        System.out.println("Student ID: " + reportCard.getStudentId());
        System.out.println("Term: " + reportCard.getTerm() + " " + reportCard.getYear());
        System.out.println("Grades: " + reportCard.getGrades());
        System.out.println("Overall Performance: " + reportCard.getOverallPerformance());
        System.out.println("=== End of Details ===");
    }

    @Override
    public void onReportCardSelected(String reportId) {
        // This method is called by UI when user selects a report.
        // In console simulation, we directly call controller from simulateParentSelectsReportCard.
        System.out.println("View: Report card selected: " + reportId);
    }

    @Override
    public void showErrorMessage(String message) {
        System.err.println("ERROR: " + message);
    }

    // Helper methods to simulate user actions
    public void simulateParentClicksChildImage(String studentId, String sessionId) {
        // Simulates Parent -> UI : clicksChildImage(studentId)
        System.out.println("UI: Parent clicked child image for student " + studentId);
        // UI then calls controller
        if (controller != null) {
            controller.showReportCardList(studentId, sessionId);
        }
    }

    public void simulateParentSelectsReportCard(String reportId, String sessionId) {
        // Simulates Parent -> UI : onReportCardSelected(reportId)
        onReportCardSelected(reportId);
        // UI then calls controller
        if (controller != null) {
            controller.showReportCardDetails(reportId, sessionId);
        }
    }
}
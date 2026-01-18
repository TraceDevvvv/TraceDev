package com.example.views;

import com.example.controllers.ReportController;
import com.example.domain.Report;
import com.example.presenters.DisplayReportPresenter;
import java.util.List;

/**
 * View component for displaying reports.
 */
public class ReportView implements DisplayReportPresenter {
    private ReportController controller;
    
    public ReportView(ReportController controller) {
        this.controller = controller;
    }
    
    /**
     * Called when user clicks "Online report" button.
     * Entry condition: User HAS clicked on 'Online report'
     * Implements message: clicks "Online report"
     */
    public void onOnlineReportClicked() {
        // Implement message m1: clicks "Online report"
        controller.displayReportsList();
    }
    
    /**
     * Called when user selects a report card from the list.
     * Implements message: selects report card
     */
    public void onReportSelected(String reportId) {
        // Implement message m22: selects report card
        controller.selectReport(reportId);
    }
    
    @Override
    public void presentReportsList(List<Report> reports) {
        // Display the list of reports to the user
        showReportsList(reports);
    }
    
    @Override
    public void presentReportDetails(Report report) {
        // Display the report details to the user
        showReportDetails(report);
    }
    
    @Override
    public void presentError(String error) {
        // Display error message to the user
        showError(error);
    }
    
    /**
     * Displays the list of reports.
     * Implements return message: displays student's reports
     */
    public void showReportsList(List<Report> reports) {
        System.out.println("=== Student Reports ===");
        for (Report report : reports) {
            System.out.println("Report ID: " + report.getId() + " - " + report.getTitle());
        }
        // In real implementation, update UI components
        // This implements m20: displays student's reports
    }
    
    /**
     * Displays details of a specific report.
     * Implements return message: displays report details
     */
    public void showReportDetails(Report report) {
        System.out.println("=== Report Details ===");
        System.out.println(report.viewDetails());
        // Quality requirement: displayed accurately and without delay
        // In real UI, this would update within 2 seconds
        // This implements m33: displays report details
    }
    
    /**
     * Displays error message.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
        // In real implementation, show error dialog or notification
    }
}
package com.example.controllers;

import com.example.auth.Session;
import com.example.domain.Report;
import com.example.presenters.DisplayReportPresenter;
import com.example.requests.DisplayReportRequest;
import com.example.usecases.DisplayReportUseCase;
import java.util.List;

/**
 * Controller handling report display requests.
 */
public class ReportController {
    private DisplayReportUseCase displayReportUseCase;
    private Session session;
    
    public ReportController(DisplayReportUseCase useCase, Session session) {
        this.displayReportUseCase = useCase;
        this.session = session;
    }
    
    /**
     * Displays list of reports for the current student.
     */
    public void displayReportsList() {
        String studentId = session.getUserId();
        
        // Validate session as per sequence diagram requirement
        if (!validateSession(studentId)) {
            // In real implementation, would notify view of session error
            return;
        }
        
        DisplayReportRequest request = new DisplayReportRequest(studentId);
        // View implements DisplayReportPresenter
        // The presenter is set through the View constructor in our implementation
    }
    
    /**
     * Selects a specific report to display details.
     */
    public void selectReport(String reportId) {
        String studentId = session.getUserId();
        
        // Validate session as per sequence diagram requirement
        if (!validateSession(studentId)) {
            return;
        }
        
        DisplayReportRequest request = new DisplayReportRequest(studentId, reportId);
        // In actual flow, the use case would be executed with a presenter
    }
    
    /**
     * Validates the current session for the given user ID.
     */
    private boolean validateSession(String userId) {
        return session.validateSession(userId);
    }
    
    // Method to execute use case with presenter (called by view)
    public void executeUseCase(DisplayReportRequest request, DisplayReportPresenter presenter) {
        displayReportUseCase.execute(request, presenter);
    }
}
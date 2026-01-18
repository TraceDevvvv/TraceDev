package com.example.reports;

import java.util.List;

/**
 * The controller handles requests related to student reports, acting as an intermediary
 * between the view and the model (repository and session).
 * It orchestrates data retrieval and updates the view.
 */
public class StudentReportController {
    private UserSession userSession;
    private IStudentReportRepository reportRepository;
    private StudentReportView view;

    /**
     * Constructs a new StudentReportController.
     *
     * @param userSession      The user session object, providing current user context.
     * @param repository       The repository for accessing student report data.
     * @param view             The view responsible for displaying information to the user.
     */
    public StudentReportController(UserSession userSession, IStudentReportRepository repository, StudentReportView view) {
        this.userSession = userSession;
        this.reportRepository = repository;
        this.view = view;
    }

    /**
     * Handles the request to retrieve and display a list of all student reports for the current user.
     * This method implements the first part of the sequence diagram for successful retrieval
     * or connection error during listing reports.
     */
    public void handleRequestReportsList() {
        System.out.println("[Controller] Handling request for reports list.");
        String studentId = userSession.getCurrentStudentId(); // Controller -> UserSession: getCurrentStudentId()
        System.out.println("[Controller] Retrieved student ID from session: " + studentId);

        try {
            // Controller -> Repository: getReportsByStudentId(studentId)
            List<StudentReport> studentReportsList = reportRepository.getReportsByStudentId(studentId);
            // Controller -> View: displayReportsList(studentReportsList)
            view.displayReportsList(studentReportsList);
        } catch (ServerConnectionError e) {
            // Controller -> View: showError("Server connection interrupted.")
            view.showError("Server connection interrupted while fetching report list: " + e.getMessage());
        }
    }

    /**
     * Handles the request to retrieve and display the details of a specific student report.
     * This method implements the second part of the sequence diagram for successful retrieval
     * or connection error during fetching specific report details.
     *
     * @param reportId The ID of the report whose details are requested.
     */
    public void handleRequestReportDetails(String reportId) {
        System.out.println("[Controller] Handling request for report details for ID: " + reportId);
        try {
            // Controller -> Repository: getReportById(reportId)
            StudentReport selectedReport = reportRepository.getReportById(reportId);
            // Controller -> View: displayReportDetails(selectedReport)
            view.displayReportDetails(selectedReport);
        } catch (ServerConnectionError e) {
            // Controller -> View: showError("Server connection interrupted.")
            view.showError("Server connection interrupted while fetching report details: " + e.getMessage());
        }
    }
}
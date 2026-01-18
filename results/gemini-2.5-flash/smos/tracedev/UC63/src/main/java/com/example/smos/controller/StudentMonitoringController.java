package com.example.smos.controller;

import com.example.smos.dto.StudentMonitoringDTO;
import com.example.smos.exception.SMOSServerConnectionException;
import com.example.smos.service.StudentMonitoringService;
import com.example.smos.view.StudentMonitoringView;

import java.util.List;

/**
 * Controller for handling student monitoring requests from the user interface.
 * This class corresponds to the 'StudentMonitoringController' class in the UML diagram.
 * It orchestrates interactions between the view and the service layer.
 */
public class StudentMonitoringController {

    private final StudentMonitoringService studentMonitoringService;
    private final StudentMonitoringView studentMonitoringView;

    /**
     * Constructs a StudentMonitoringController with necessary dependencies.
     * Dependencies are injected via the constructor.
     *
     * @param studentMonitoringService The service responsible for business logic.
     * @param studentMonitoringView The view responsible for displaying information.
     */
    public StudentMonitoringController(StudentMonitoringService studentMonitoringService, StudentMonitoringView studentMonitoringView) {
        this.studentMonitoringService = studentMonitoringService;
        this.studentMonitoringView = studentMonitoringView;
    }

    /**
     * Handles the request to generate and display a student monitoring report.
     * This method delegates to the service layer and updates the view with results or errors.
     * This method handles the {@link SMOSServerConnectionException} as per REQ-008 and the sequence diagram.
     *
     * @param absenceThreshold The threshold for considering absences as 'high'.
     * @param noteThreshold The threshold for considering notes as 'high'.
     */
    public void requestStudentMonitoringReport(int absenceThreshold, int noteThreshold) {
        System.out.println("\nController: Request received for student monitoring report (Absence Threshold: " +
                           absenceThreshold + ", Note Threshold: " + noteThreshold + ")");

        try {
            // Delegate the request to the service layer to get filtered student data.
            List<StudentMonitoringDTO> filteredStudents =
                    studentMonitoringService.getHighPerformanceStudents(absenceThreshold, noteThreshold);

            // Update the view with the successfully retrieved student data.
            studentMonitoringView.displayStudents(filteredStudents);
            System.out.println("Controller: Monitoring Report Displayed (to Direction).\n");

        } catch (SMOSServerConnectionException e) {
            // If a server connection exception occurs, display an error message via the view.
            System.err.println("Controller: Caught SMOSServerConnectionException: " + e.getMessage());
            studentMonitoringView.displayErrorMessage("Failed to connect to SMOS server. Please try again.");
            System.err.println("Controller: Error: SMOS Server Unavailable (to Direction).\n");
        }
    }
}
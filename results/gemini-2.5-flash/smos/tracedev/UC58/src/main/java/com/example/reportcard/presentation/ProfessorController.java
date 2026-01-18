package com.example.reportcard.presentation;

import com.example.reportcard.dto.AcademicYearDTO;
import com.example.reportcard.dto.ClassDTO;
import com.example.reportcard.dto.ReportCardDTO;
import com.example.reportcard.dto.StudentDTO;
import com.example.reportcard.exception.NetworkException;
import com.example.reportcard.service.ReportCardService;
import com.example.reportcard.service.SessionManager;

import java.util.List;

/**
 * Presentation layer controller for Professor interactions related to report cards.
 * Handles user input, orchestrates application service calls, and updates the view.
 */
public class ProfessorController {
    private String selectedAcademicYearId;
    private String selectedClassId;
    private String selectedStudentId;
    private String selectedQuarter;

    private final ReportCardService reportCardService;
    private final SessionManager sessionManager;
    private final ReportCardView reportCardView;
    private final ReportCardViewModel reportCardViewModel;

    public ProfessorController(ReportCardService reportCardService,
                               SessionManager sessionManager,
                               ReportCardView reportCardView,
                               ReportCardViewModel reportCardViewModel) {
        this.reportCardService = reportCardService;
        this.sessionManager = sessionManager;
        this.reportCardView = reportCardView;
        this.reportCardViewModel = reportCardViewModel;

        // Initialize view with controller for interaction (e.g., button clicks)
        this.reportCardView.setController(this);
    }

    /**
     * Initiates the request to fetch academic years for the current professor.
     * Triggered by the UI (e.g., "On-line report cards" button click).
     */
    public void requestAcademicYears() {
        System.out.println("\n[Controller] Requesting academic years...");
        try {
            // Check network connectivity first, as per sequence diagram
            reportCardService.checkNetworkConnectivity();

            String professorId = sessionManager.getProfessorId();
            List<AcademicYearDTO> academicYearsDTOs = reportCardService.getProfessorAcademicYears(professorId);

            reportCardViewModel.updateAcademicYears(academicYearsDTOs);
            reportCardView.showAcademicYears(academicYearsDTOs);
        } catch (NetworkException e) {
            reportCardView.displayErrorMessage("Connection interrupted: " + e.getMessage());
        } catch (Exception e) {
            reportCardView.displayErrorMessage("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the selection of an academic year by the professor.
     * This action also triggers fetching classes for the selected year and professor.
     * @param yearId The ID of the selected academic year.
     */
    public void selectAcademicYear(String yearId) {
        this.selectedAcademicYearId = yearId;
        System.out.println("\n[Controller] Academic year selected: " + yearId);
        try {
            // Check network connectivity
            reportCardService.checkNetworkConnectivity();

            String professorId = sessionManager.getProfessorId();
            List<ClassDTO> classesDTOs = reportCardService.getClassesForAcademicYearAndProfessor(yearId, professorId);

            reportCardViewModel.updateClasses(classesDTOs);
            reportCardView.showClasses(classesDTOs);
        } catch (NetworkException e) {
            reportCardView.displayErrorMessage("Connection interrupted: " + e.getMessage());
        } catch (Exception e) {
            reportCardView.displayErrorMessage("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the selection of a class by the professor.
     * This action also triggers fetching students for the selected class.
     * @param classId The ID of the selected class.
     */
    public void selectClass(String classId) {
        this.selectedClassId = classId;
        System.out.println("\n[Controller] Class selected: " + classId);
        try {
            // Check network connectivity
            reportCardService.checkNetworkConnectivity();

            List<StudentDTO> studentsDTOs = reportCardService.getStudentsForClass(classId);

            reportCardViewModel.updateStudents(studentsDTOs);
            reportCardView.showStudents(studentsDTOs);
        } catch (NetworkException e) {
            reportCardView.displayErrorMessage("Connection interrupted: " + e.getMessage());
        } catch (Exception e) {
            reportCardView.displayErrorMessage("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the selection of a student by the professor.
     * Stores the student ID for later report card retrieval.
     * @param studentId The ID of the selected student.
     */
    public void selectStudent(String studentId) {
        this.selectedStudentId = studentId;
        System.out.println("\n[Controller] Student selected: " + studentId);
    }

    /**
     * Handles the selection of a quadrimestre (quarter) by the professor.
     * Stores the quarter for later report card retrieval.
     * @param quarter The selected quarter (e.g., "Q1", "Q2").
     */
    public void selectQuadrimestre(String quarter) {
        this.selectedQuarter = quarter;
        System.out.println("\n[Controller] Quarter selected: " + quarter);
    }

    /**
     * Triggers the display of the report card for the currently selected student,
     * academic year, and quarter.
     * This method is typically called when the professor clicks a "Display Report Card" button.
     */
    public void displayReportCard() {
        System.out.println("\n[Controller] Displaying report card...");
        // Ensure all selections are made before attempting to display
        if (selectedStudentId == null || selectedAcademicYearId == null || selectedQuarter == null) {
            reportCardView.displayErrorMessage("Please select an academic year, class, student, and quarter.");
            return;
        }

        try {
            // Check network connectivity
            reportCardService.checkNetworkConnectivity();

            ReportCardDTO reportCardDTO = reportCardService.retrieveStudentReportCard(
                    selectedStudentId, selectedAcademicYearId, selectedQuarter);

            if (reportCardDTO != null) {
                reportCardViewModel.updateReportCard(reportCardDTO);
                reportCardView.showReportCard(reportCardDTO);
            } else {
                reportCardView.displayErrorMessage("Report card not found for the selected criteria.");
            }
        } catch (NetworkException e) {
            reportCardView.displayErrorMessage("Connection interrupted: " + e.getMessage());
        } catch (Exception e) {
            reportCardView.displayErrorMessage("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
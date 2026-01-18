
package com.example.reportcard.presentation;

import com.example.reportcard.application.ReportCardService;
import com.example.reportcard.domain.AcademicYear;
import com.example.reportcard.domain.Class;
import com.example.reportcard.domain.Month;
import com.example.reportcard.domain.ReportCard;
import com.example.reportcard.domain.Student;

import java.util.List;

/**
 * The ReportCardController handles user interactions from the ReportCardView
 * and orchestrates calls to the ReportCardService. It maintains the state of the selections.
 */
public class ReportCardController {
    private ReportCardService reportCardService;
    private ReportCardView reportCardView; // Reference to the view for callbacks

    // Controller state to hold current selections
    private String selectedAcademicYearId;
    private String selectedClassId;
    private String selectedStudentId;
    private List<Month> selectedMonths;

    /**
     * Constructs a ReportCardController.
     *
     * @param service The ReportCardService instance to interact with the application layer.
     */
    public ReportCardController(ReportCardService service) {
        this.reportCardService = service;
    }

    /**
     * Sets the view associated with this controller. This is needed for the controller
     * to call back to the view for displaying information.
     *
     * @param view The ReportCardView instance.
     */
    public void setView(ReportCardView view) {
        this.reportCardView = view;
    }

    /**
     * Handles the event when the "Online Reports" button is clicked.
     * Initiates the process of fetching academic years.
     */
    public void handleOnlineReportsClick() {
        System.out.println("Controller: Handling 'Online Reports' click.");
        try {
            List<AcademicYear> academicYearsList = reportCardService.getAcademicYears();
            reportCardView.displayAcademicYears(academicYearsList);
            // Reset selections when starting a new report generation flow
            resetSelections();
        } catch (Exception e) {
            reportCardView.displayError("Failed to retrieve academic years: " + e.getMessage());
        }
    }

    /**
     * Handles the selection of an academic year by the user.
     * Fetches and displays classes for the selected year.
     *
     * @param yearId The ID of the selected academic year.
     */
    public void handleAcademicYearSelection(String yearId) {
        System.out.println("Controller: Handling academic year selection: " + yearId);
        this.selectedAcademicYearId = yearId;
        // Clear subsequent selections
        this.selectedClassId = null;
        this.selectedStudentId = null;
        this.selectedMonths = null;
        
        try {
            List<Class> classesList = reportCardService.getClassesByAcademicYear(yearId);
            reportCardView.displayClasses(classesList);
        } catch (Exception e) {
            reportCardView.displayError("Failed to retrieve classes for year " + yearId + ": " + e.getMessage());
        }
    }

    /**
     * Handles the selection of a class by the user.
     * Fetches and displays students for the selected class.
     *
     * @param classId The ID of the selected class.
     */
    public void handleClassSelection(String classId) {
        System.out.println("Controller: Handling class selection: " + classId);
        this.selectedClassId = classId;
        // Clear subsequent selections
        this.selectedStudentId = null;
        this.selectedMonths = null;

        try {
            List<Student> studentsList = reportCardService.getStudentsByClass(classId);
            reportCardView.displayStudents(studentsList);
        } catch (Exception e) {
            reportCardView.displayError("Failed to retrieve students for class " + classId + ": " + e.getMessage());
        }
    }

    /**
     * Handles the selection of a student by the user.
     * This marks the student as selected and prompts for month selection.
     *
     * @param studentId The ID of the selected student.
     */
    public void handleStudentSelection(String studentId) {
        System.out.println("Controller: Handling student selection: " + studentId);
        this.selectedStudentId = studentId;
        this.selectedMonths = null; // Clear previous month selection
        reportCardView.displayMonthSelection(); // Prompt view to show month selection
    }

    /**
     * Handles the selection of months by the user.
     *
     * @param months A list of selected Month objects.
     */
    public void handleMonthSelection(List<Month> months) {
        System.out.println("Controller: Handling month selection: " + months);
        this.selectedMonths = months;
    }

    /**
     * Handles the event when the "Generate Report" button is clicked.
     * Fetches the report card and displays it or an error.
     * The `studentId`, `academicYearId`, and `months` parameters are passed directly
     * from the view's current selection state, as implied by the sequence diagram.
     * However, the controller also maintains its own state, so it could alternatively use
     * `selectedStudentId`, `selectedAcademicYearId`, and `selectedMonths`.
     * For direct adherence to the sequence diagram's parameters, we use the passed ones.
     *
     * @param studentId The ID of the student for whom to generate the report.
     * @param academicYearId The ID of the academic year for the report.
     * @param months The list of months for the report period.
     */
    public void handleGenerateReportClick(String studentId, String academicYearId, List<Month> months) {
        System.out.println("Controller: Handling 'Generate Report' click for student " + studentId +
                           ", year " + academicYearId + ", months " + months);
        
        // Validate that all necessary selections have been made
        if (studentId == null || academicYearId == null || months == null || months.isEmpty()) {
            reportCardView.displayError("Please ensure an academic year, class, student, and months are selected.");
            return;
        }

        try {
            ReportCard reportCard = reportCardService.generateReportCard(studentId, academicYearId, months);
            if (reportCard != null) {
                reportCardView.displayReport(reportCard);
            } else {
                reportCardView.displayError("No report card found for the selected criteria.");
            }
        } catch (Exception e) {
            reportCardView.displayError("Failed to generate report: " + e.getMessage());
        }
    }

    /**
     * Resets the controller's internal state for selections.
     */
    private void resetSelections() {
        this.selectedAcademicYearId = null;
        this.selectedClassId = null;
        this.selectedStudentId = null;
        this.selectedMonths = null;
    }
}

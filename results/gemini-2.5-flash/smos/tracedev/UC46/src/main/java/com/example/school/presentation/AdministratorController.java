package com.example.school.presentation;

import com.example.school.application.ReportCardApplicationService;
import com.example.school.domain.Class;
import com.example.school.domain.Student;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList; // For grades in reportCardData

/**
 * Controller for Administrator actions related to report cards.
 * Mediates between the Administrator (via UI) and the Application Service.
 */
public class AdministratorController {
    // Dependencies
    private ReportCardApplicationService reportCardAppService;
    private ReportCardView reportCardView;
    private ReportCardViewModel reportCardViewModel;

    /**
     * Constructs an AdministratorController, injecting its dependencies.
     * @param reportCardAppService The application service for report card operations.
     * @param reportCardView The view component for displaying information.
     * @param reportCardViewModel The view model for managing UI state.
     */
    public AdministratorController(
            ReportCardApplicationService reportCardAppService,
            ReportCardView reportCardView,
            ReportCardViewModel reportCardViewModel) {
        this.reportCardAppService = reportCardAppService;
        this.reportCardView = reportCardView;
        this.reportCardViewModel = reportCardViewModel;
    }

    /**
     * Initiates the process of displaying available classes.
     * Triggered by "Online reports" button click.
     * Assumes Administrator is logged in.
     */
    public void displayClasses() {
        System.out.println("\nAdministratorController: Administrator clicked 'Online reports'.");
        reportCardViewModel.reset(); // Clear previous state

        String academicYearId = reportCardViewModel.getCurrentAcademicYearId();
        // 1. UI -> AppService: getClassesForCurrentAcademicYear
        List<Class> classes = reportCardAppService.getClassesForCurrentAcademicYear(academicYearId);
        reportCardViewModel.setClasses(classes);

        // 2. AppService -> UI: displayClasses
        reportCardView.displayClasses(classes);
    }

    /**
     * Handles the selection of a class by the administrator.
     * @param classId The ID of the selected class.
     */
    public void selectClass(String classId) {
        System.out.println("\nAdministratorController: Administrator selected class: " + classId);
        reportCardViewModel.setSelectedClassId(classId);

        // 1. UI -> AppService: getStudentsInClass
        List<Student> students = reportCardAppService.getStudentsInClass(classId);
        reportCardViewModel.setStudents(students);

        // 2. AppService -> UI: displayStudents
        reportCardView.displayStudents(students);
    }

    /**
     * Handles the selection of a student by the administrator.
     * @param studentId The ID of the selected student.
     */
    public void selectStudent(String studentId) {
        System.out.println("\nAdministratorController: Administrator selected student: " + studentId);
        reportCardViewModel.setSelectedStudentId(studentId);
    }

    /**
     * Displays the report card entry form for a specific student.
     * This method assumes the student has already been selected via `selectStudent`.
     * If this method is called directly, `studentId` should be fetched or passed.
     * @param studentId The ID of the student for whom the form is to be displayed.
     */
    public void displayReportCardEntryForm(String studentId) {
        System.out.println("\nAdministratorController: Administrator clicked 'Insert Report Card' for student: " + studentId);

        // Fetch student details to populate the form (e.g., student name)
        Student student = reportCardAppService.getStudentById(studentId);
        if (student == null) {
            reportCardView.displayErrorMessage("Student with ID " + studentId + " not found.");
            return;
        }

        reportCardView.displayReportCardForm(student);

        // Prepare initial data for the form in the view model
        Map<String, Object> initialReportCardData = new HashMap<>();
        initialReportCardData.put("studentId", student.getId());
        initialReportCardData.put("academicYearId", reportCardViewModel.getCurrentAcademicYearId());
        reportCardViewModel.setReportCardData(initialReportCardData);
    }

    /**
     * Saves the entered report card data.
     * Triggered by Administrator clicking 'Save' button after entering grades.
     * @param reportCardData A map containing all report card details from the form.
     */
    public void saveReportCard(Map<String, Object> reportCardData) {
        System.out.println("\nAdministratorController: Administrator clicked 'Save' report card.");
        reportCardViewModel.setReportCardData(reportCardData); // Update view model with entered data

        // 1. UI -> AppService: saveReportCard
        boolean success = reportCardAppService.saveReportCard(reportCardData);

        // 2. AppService -> UI: return boolean (true/false)
        if (success) {
            reportCardView.displaySuccessMessage("Report card saved successfully.");
            // After successful save, navigate back to the list of students in the class
            String selectedClassId = reportCardViewModel.getSelectedClassId();
            if (selectedClassId != null) {
                reportCardView.navigateToClassStudentView(selectedClassId);
                // Re-display students for the selected class to refresh the list if needed
                selectClass(selectedClassId);
            } else {
                displayClasses(); // Fallback to displaying all classes
            }
        } else {
            reportCardView.displayErrorMessage("Failed to save report card. Please check the data.");
            // Optionally, re-display the form with error messages
            // reportCardView.displayReportCardForm(reportCardAppService.getStudentById((String) reportCardData.get("studentId")));
        }
    }
}
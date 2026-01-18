package com.example.reportcard.presentation;

import com.example.reportcard.domain.ReportCardDTO;
import java.util.Map;

/**
 * Boundary class representing the UI for editing a report card.
 */
public class ReportCardEditView {
    private EditReportCardController controller;
    private ReportCardDTO currentData;

    public void setController(EditReportCardController controller) {
        this.controller = controller;
    }

    // Called when Administrator clicks edit button
    public void onEditButtonClicked(String studentId) {
        controller.handleEditRequest(studentId);
    }

    // Display the edit form with existing grades
    public void displayEditForm(ReportCardDTO reportCardData) {
        this.currentData = reportCardData;
        System.out.println("Displaying edit form for student: " + reportCardData.getStudentId());
        System.out.println("Grades: " + reportCardData.getSubjectGrades());
    }
    
    public void displayFormWithFieldsAndExistingGrades(ReportCardDTO reportCardData) {
        this.currentData = reportCardData;
        System.out.println("Displaying form with fields and existing grades for student: " + reportCardData.getStudentId());
        System.out.println("Grades: " + reportCardData.getSubjectGrades());
    }
    
    public void entersNewData(Map<String, Integer> newGrades) {
        this.currentData = new ReportCardDTO(currentData.getStudentId(), newGrades);
    }
    
    public void clicksConfirmationKey() {
        controller.handleSaveRequest(currentData);
    }

    // Administrator enters data and clicks confirmation key
    public void submitChanges(Map<String, Integer> newGrades) {
        ReportCardDTO dto = new ReportCardDTO(currentData.getStudentId(), newGrades);
        controller.handleSaveRequest(dto);
    }

    // Retrieve entered data (simulated)
    public ReportCardDTO getEnteredData() {
        // In a real UI, this would collect data from input fields
        return currentData;
    }
    
    public void showsReportModifiedSuccessfullyMessage() {
        System.out.println("Report modified successfully");
    }

    public void displayConfirmation() {
        System.out.println("Report modified successfully");
    }
    
    public void displayFormWithListOfStudents() {
        System.out.println("Displaying form with list of students");
    }

    public void displayError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void displayStudentList() {
        System.out.println("Displaying list of students");
    }
}
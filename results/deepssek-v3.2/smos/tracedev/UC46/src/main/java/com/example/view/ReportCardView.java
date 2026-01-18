package com.example.view;

import com.example.dto.ClassDTO;
import com.example.dto.ReportCardDTO;
import com.example.dto.StudentDTO;
import com.example.viewmodel.FormViewModel;
import java.util.List;

/**
 * View class for Report Card operations.
 */
public class ReportCardView {
    private com.example.viewmodel.ReportCardViewModel viewModel;

    public ReportCardView(com.example.viewmodel.ReportCardViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Displays the list of classes.
     * @param classes the list of classes to display
     */
    public void displayClasses(List<ClassDTO> classes) {
        // Implementation to display classes list with "report cards" buttons
        System.out.println("Displaying classes list with \"report cards\" buttons:");
        for (ClassDTO classDto : classes) {
            System.out.println("Class: " + classDto.name + " (ID: " + classDto.id + ", Year: " + classDto.academicYear + ")");
        }
    }

    /**
     * Displays the list of students.
     * @param students the list of students to display
     */
    public void displayStudents(List<StudentDTO> students) {
        // Implementation to display class students list
        System.out.println("Displaying class students list:");
        for (StudentDTO studentDto : students) {
            System.out.println("Student: " + studentDto.fullName + " (ID: " + studentDto.id + ")");
        }
    }

    /**
     * Displays the report card form.
     * @return the FormViewModel for the report card form
     */
    public FormViewModel displayReportCardForm() {
        // Implementation to display the report card form
        System.out.println("Displaying report card form.");
        // Return a new FormViewModel (this can be populated later)
        return new FormViewModel();
    }

    /**
     * Gets the form data from the view.
     * @return the ReportCardDTO containing the form data
     */
    public ReportCardDTO getFormData() {
        // Implementation to collect form data from the UI and convert to ReportCardDTO
        // For now, return a dummy DTO
        System.out.println("Getting form data from the view.");
        return new ReportCardDTO();
    }

    /**
     * Shows a success message.
     */
    public void showSuccess() {
        System.out.println("Report card saved successfully. Redirecting to class student view.");
    }

    /**
     * Shows an error message.
     * @param message the error message to display
     */
    public void showError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Cancels the current operation.
     */
    public void cancelOperation() {
        // Clean up the view and cancel the operation
        System.out.println("Operation cancelled.");
    }

    /**
     * Returns to the dashboard.
     */
    public void returnToDashboard() {
        System.out.println("Returning to dashboard.");
    }
}
package presentation;

import domain.ReportCard;
import domain.Subject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mock View class for displaying and interacting with the Report Card edit form.
 */
public class ReportCardEditView {
    private ReportCardController controller; // Reference back to the controller

    /**
     * Sets the controller for this view to trigger actions.
     * @param controller The ReportCardController instance.
     */
    public void setController(ReportCardController controller) {
        this.controller = controller;
    }

    /**
     * Displays the report card editing form with current grades and available subjects.
     * @param reportCard The ReportCard object to display.
     * @param subjects A list of all available subjects.
     */
    public void displayEditForm(ReportCard reportCard, List<Subject> subjects) {
        System.out.println("\n--- Report Card Edit Form ---");
        System.out.println("Student ID: " + reportCard.getStudentId());
        System.out.println("Report Card ID: " + reportCard.getId());
        System.out.println("Current Grades:");
        for (Subject subject : subjects) {
            Integer grade = reportCard.getGrade(subject.getId());
            System.out.println("  " + subject.getName() + " (" + subject.getId() + "): " + (grade != null ? grade : "N/A"));
        }
        System.out.println("-----------------------------");
        System.out.println("Please enter new grades or confirm changes.");
        // In a real UI, this would render input fields.
    }

    /**
     * Displays a confirmation message to the user.
     * @param message The confirmation message.
     */
    public void displayConfirmation(String message) {
        System.out.println("\nSUCCESS: " + message);
    }

    /**
     * Displays an error message to the user.
     * Added for consistency with CD and REQ-006.
     * @param message The error message.
     */
    public void displayError(String message) {
        System.err.println("\nERROR: " + message);
    }

    /**
     * Simulates displaying a list of students, typically after an operation.
     */
    public void displayStudentList() {
        System.out.println("\n--- Student List ---");
        System.out.println("Displaying a list of all students. (Mock)");
        // In a real UI, this would navigate back or refresh a list.
    }

    /**
     * Simulates getting form data from user input.
     * For demonstration, it returns a hardcoded map of new grades.
     * Added to satisfy REQ-009.
     * @return A map where keys are Subject IDs and values are integer grades.
     */
    public Map<String, Integer> getFormData() {
        System.out.println("\nDEBUG: View: Simulating form data submission (e.g., Math: 90, English: 95).");
        Map<String, Integer> newGradesData = new HashMap<>();
        newGradesData.put("MATH101", 90); // Example new grade for Math
        newGradesData.put("ENG202", 95); // Example new grade for English
        // Other subjects not in the map would remain unchanged or be removed,
        // depending on business rules (here, existing grades for subjects not in newGradesData are preserved).
        return newGradesData;
    }

    /**
     * Simulates clicking the edit button for a student.
     * @param studentId The ID of the student to edit.
     */
    public void clickEditButton(String studentId) {
        System.out.println("\nAdmin clicks edit button for student ID: " + studentId);
        if (controller != null) {
            // The controller handles the logic of displaying the form
            controller.editReportCard(studentId);
        }
    }

    /**
     * Simulates clicking the confirmation button after entering data.
     * This method will also call `getFormData()` internally.
     * @param studentId The ID of the student whose report card is being changed.
     */
    public void clickConfirmationButton(String studentId) {
        System.out.println("\nAdmin clicks confirmation button for student ID: " + studentId);
        if (controller != null) {
            Map<String, Integer> newGrades = getFormData();
            controller.submitReportCardChanges(studentId, newGrades);
        }
    }
}
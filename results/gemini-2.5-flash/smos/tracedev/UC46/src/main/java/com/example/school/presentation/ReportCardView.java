package com.example.school.presentation;

import com.example.school.domain.Class;
import com.example.school.domain.Student;

import java.util.List;
import java.util.Map;

/**
 * Represents the view component for displaying report card related information to the Administrator.
 * In a real application, this would be a GUI or web page. Here, it's a console output mock.
 */
public class ReportCardView {

    /**
     * Displays a list of classes to the administrator.
     * @param classes The list of Class objects to display.
     */
    public void displayClasses(List<Class> classes) {
        System.out.println("\n--- Available Classes (Report Card Entry) ---");
        if (classes == null || classes.isEmpty()) {
            System.out.println("No classes found for the current academic year.");
            return;
        }
        for (Class c : classes) {
            System.out.println("ID: " + c.getId() + ", Name: " + c.getName() + " (Year: " + c.getAcademicYearId() + ")");
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * Displays a list of students within a selected class.
     * @param students The list of Student objects to display.
     */
    public void displayStudents(List<Student> students) {
        System.out.println("\n--- Students in Selected Class ---");
        if (students == null || students.isEmpty()) {
            System.out.println("No students found in this class.");
            return;
        }
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Name: " + s.getName());
        }
        System.out.println("----------------------------------");
    }

    /**
     * Displays a form for entering report card details for a specific student.
     * @param student The student for whom the report card is being created/edited.
     */
    public void displayReportCardForm(Student student) {
        System.out.println("\n--- Report Card Entry Form ---");
        if (student == null) {
            System.out.println("Error: No student selected for report card entry.");
            return;
        }
        System.out.println("Entering Report Card for: " + student.getName() + " (ID: " + student.getId() + ")");
        System.out.println("Please provide academic year ID, term, and grades (subjectId, score).");
        System.out.println("------------------------------");
        // In a real UI, this would render input fields.
    }

    /**
     * Displays a success message to the administrator.
     * @param message The success message.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("\n[SUCCESS] " + message);
    }

    /**
     * Displays an error message to the administrator.
     * @param message The error message.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n[ERROR] " + message);
    }

    /**
     * Simulates navigating back to a class/student list view, typically after an action.
     * @param classId The ID of the class to navigate back to (or display its students).
     */
    public void navigateToClassStudentView(String classId) {
        System.out.println("\nNavigating back to class '" + classId + "' student list view...");
        // In a real application, this would trigger a UI navigation event.
    }
}
package com.example.reportcard.presentation;

import com.example.reportcard.dto.AcademicYearDTO;
import com.example.reportcard.dto.ClassDTO;
import com.example.reportcard.dto.ReportCardDTO;
import com.example.reportcard.dto.StudentDTO;

import java.util.List;

/**
 * Presentation layer view for displaying report card related information to the professor.
 * This is a simplified console-based view for demonstration purposes.
 */
public class ReportCardView {
    private ProfessorController controller; // Controller to notify of user actions

    // This method is for dependency injection from the Controller
    public void setController(ProfessorController controller) {
        this.controller = controller;
    }

    /**
     * Simulates the initial display of the UI for the professor to start interaction.
     */
    public void renderInitialView() {
        System.out.println("\n--- Report Card System ---");
        System.out.println("Welcome, Professor!");
        System.out.println("1. Request Academic Years (Implicit on initial load, or click 'On-line report cards')");
        // In a real UI, these would be buttons/dropdowns. Here, controller method calls simulate clicks.
    }

    /**
     * Displays a list of academic years.
     * @param years The list of AcademicYearDTOs to display.
     */
    public void showAcademicYears(List<AcademicYearDTO> years) {
        System.out.println("\n--- Available Academic Years ---");
        if (years.isEmpty()) {
            System.out.println("No academic years found.");
            return;
        }
        years.forEach(year -> System.out.println("ID: " + year.getId() + ", Year: " + year.getYear()));
        System.out.println("--- End Academic Years ---");
    }

    /**
     * Displays a list of classes.
     * @param classes The list of ClassDTOs to display.
     */
    public void showClasses(List<ClassDTO> classes) {
        System.out.println("\n--- Classes for Selected Academic Year ---");
        if (classes.isEmpty()) {
            System.out.println("No classes found for this academic year.");
            return;
        }
        classes.forEach(cls -> System.out.println("ID: " + cls.getId() + ", Name: " + cls.getName()));
        System.out.println("--- End Classes ---");
    }

    /**
     * Displays a list of students.
     * @param students The list of StudentDTOs to display.
     */
    public void showStudents(List<StudentDTO> students) {
        System.out.println("\n--- Students in Selected Class ---");
        if (students.isEmpty()) {
            System.out.println("No students found in this class.");
            return;
        }
        students.forEach(student -> System.out.println("ID: " + student.getId() + ", Name: " + student.getName()));
        System.out.println("--- End Students ---");
    }

    /**
     * Displays a student's report card.
     * @param report The ReportCardDTO to display.
     */
    public void showReportCard(ReportCardDTO report) {
        System.out.println("\n--- Student Report Card ---");
        System.out.println("Student: " + report.getStudentName());
        System.out.println("Academic Year: " + report.getAcademicYear());
        System.out.println("Quarter: " + report.getQuarter());
        System.out.println("Grades:");
        report.getGrades().forEach((subject, grade) -> System.out.println("  - " + subject + ": " + grade));
        System.out.println("--- End Report Card ---");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!");
    }
}
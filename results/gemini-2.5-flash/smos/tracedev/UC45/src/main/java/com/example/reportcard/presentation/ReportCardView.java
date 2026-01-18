package com.example.reportcard.presentation;

import com.example.reportcard.domain.AcademicYear;
import com.example.reportcard.domain.Class;
import com.example.reportcard.domain.Month;
import com.example.reportcard.domain.ReportCard;
import com.example.reportcard.domain.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The ReportCardView is responsible for displaying information to the user
 * and capturing user input. It interacts with the ReportCardController.
 * This is a console-based implementation for demonstration purposes.
 */
public class ReportCardView {
    private ReportCardController reportCardController;

    // View state to track current selections for generating report in clickGenerateReport()
    private String currentSelectedAcademicYearId;
    private String currentSelectedClassId;
    private String currentSelectedStudentId;
    private List<Month> currentSelectedMonths;

    /**
     * Constructs a ReportCardView and sets its controller.
     *
     * @param controller The ReportCardController to which user actions will be delegated.
     */
    public ReportCardView(ReportCardController controller) {
        this.reportCardController = controller;
        this.reportCardController.setView(this); // Let the controller know about this view instance
    }

    /**
     * Displays a list of academic years to the user.
     *
     * @param years The list of AcademicYear objects to display.
     */
    public void displayAcademicYears(List<AcademicYear> years) {
        System.out.println("\n--- Academic Years ---");
        if (years == null || years.isEmpty()) {
            System.out.println("No academic years available.");
            return;
        }
        for (int i = 0; i < years.size(); i++) {
            System.out.println((i + 1) + ". " + years.get(i).getYear() + " (ID: " + years.get(i).getId() + ")");
        }
        System.out.println("----------------------");
    }

    /**
     * Displays a list of classes to the user.
     *
     * @param classes The list of Class objects to display.
     */
    public void displayClasses(List<Class> classes) {
        System.out.println("\n--- Classes ---");
        if (classes == null || classes.isEmpty()) {
            System.out.println("No classes available for this academic year.");
            return;
        }
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).getName() + " (ID: " + classes.get(i).getId() + ")");
        }
        System.out.println("-----------------");
    }

    /**
     * Displays a list of students to the user.
     *
     * @param students The list of Student objects to display.
     */
    public void displayStudents(List<Student> students) {
        System.out.println("\n--- Students ---");
        if (students == null || students.isEmpty()) {
            System.out.println("No students available for this class.");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName() + " (ID: " + students.get(i).getId() + ")");
        }
        System.out.println("------------------");
    }

    /**
     * Displays a prompt for month selection.
     */
    public void displayMonthSelection() {
        System.out.println("\n--- Select Months ---");
        System.out.println("Available months: Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec");
        System.out.println("Enter months as comma-separated values (e.g., Jan,Feb,Mar,Apr):");
        System.out.println("---------------------");
    }

    /**
     * Displays the generated report card data.
     *
     * @param reportData The ReportCard object to display.
     */
    public void displayReport(ReportCard reportData) {
        System.out.println("\n" + reportData.getReportDetails());
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.err.println("\nERROR: " + message);
    }

    // --- Simulated User Actions (called by Main for demonstration) ---

    /**
     * Simulates a user clicking on "Online Reports".
     */
    public void clickOnlineReports() {
        System.out.println("\nUser Action: Clicked 'Online Reports'");
        reportCardController.handleOnlineReportsClick();
    }

    /**
     * Simulates a user selecting an academic year.
     *
     * @param yearId The ID of the academic year selected.
     */
    public void selectAcademicYear(String yearId) {
        System.out.println("User Action: Selected Academic Year " + yearId);
        this.currentSelectedAcademicYearId = yearId; // Update view state
        reportCardController.handleAcademicYearSelection(yearId);
    }

    /**
     * Simulates a user selecting a class.
     *
     * @param classId The ID of the class selected.
     */
    public void selectClass(String classId) {
        System.out.println("User Action: Selected Class " + classId);
        this.currentSelectedClassId = classId; // Update view state
        reportCardController.handleClassSelection(classId);
    }

    /**
     * Simulates a user selecting a student.
     *
     * @param studentId The ID of the student selected.
     */
    public void selectStudent(String studentId) {
        System.out.println("User Action: Selected Student " + studentId);
        this.currentSelectedStudentId = studentId; // Update view state
        reportCardController.handleStudentSelection(studentId);
    }

    /**
     * Simulates a user selecting months.
     *
     * @param monthNames A list of month names (e.g., "Jan", "Feb").
     */
    public void selectMonths(List<String> monthNames) {
        List<Month> months = monthNames.stream()
                                       .map(name -> new Month(name, getMonthNumber(name)))
                                       .collect(Collectors.toList());
        System.out.println("User Action: Selected Months " + months);
        this.currentSelectedMonths = months; // Update view state
        reportCardController.handleMonthSelection(months);
    }

    /**
     * Simulates a user clicking on "Generate Report".
     * Uses the internally tracked selected IDs and months.
     */
    public void clickGenerateReport() {
        System.out.println("User Action: Clicked 'Generate Report'");
        reportCardController.handleGenerateReportClick(
            currentSelectedStudentId,
            currentSelectedAcademicYearId,
            currentSelectedMonths
        );
    }

    /**
     * Helper method to get month number from month name (simplified).
     * @param monthName Abbreviated month name (e.g., "Jan").
     * @return Month number (1-12), or 0 if not found.
     */
    private int getMonthNumber(String monthName) {
        return switch (monthName) {
            case "Jan" -> 1;
            case "Feb" -> 2;
            case "Mar" -> 3;
            case "Apr" -> 4;
            case "May" -> 5;
            case "Jun" -> 6;
            case "Jul" -> 7;
            case "Aug" -> 8;
            case "Sep" -> 9;
            case "Oct" -> 10;
            case "Nov" -> 11;
            case "Dec" -> 12;
            default -> 0;
        };
    }
}
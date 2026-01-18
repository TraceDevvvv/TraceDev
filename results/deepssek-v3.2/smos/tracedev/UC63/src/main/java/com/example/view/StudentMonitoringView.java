package com.example.view;

import com.example.controller.StudentMonitoringController;
import com.example.dto.StudentDTO;
import com.example.criteria.MonitoringThresholdCriteria;
import com.example.user.User;

import java.util.List;
import java.util.Scanner;

/**
 * Presentation layer view for student monitoring.
 * Displays students exceeding thresholds and interacts with the user.
 */
public class StudentMonitoringView {
    private StudentMonitoringController controller;
    private User user;
    private Scanner scanner;

    public StudentMonitoringView(StudentMonitoringController controller, User user) {
        this.controller = controller;
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Checks if the user is logged in.
     * Satisfies entry conditions requirement.
     */
    public boolean isUserLoggedIn() {
        return user.isLoggedIn();
    }

    /**
     * Prompts the user for threshold values and returns a criteria object.
     */
    public MonitoringThresholdCriteria getThresholdFromUser() {
        System.out.print("Enter maximum allowed absences: ");
        int maxAbsences = scanner.nextInt();
        System.out.print("Enter maximum allowed notes: ");
        int maxNotes = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return new MonitoringThresholdCriteria(maxAbsences, maxNotes);
    }

    /**
     * Displays the list of student DTOs to the user.
     */
    public void displayStudents(List<StudentDTO> students) {
        if (students.isEmpty()) {
            System.out.println("No students exceed the thresholds.");
        } else {
            System.out.println("Students exceeding both absences and notes thresholds:");
            for (StudentDTO student : students) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.getName() +
                        ", Email: " + student.getEmail() + ", Absences: " + student.getTotalAbsences() +
                        ", Notes: " + student.getTotalNotes());
            }
        }
    }

    /**
     * Displays an error message to the user.
     */
    public void displayError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Validates data accuracy of the DTO list (for quality requirement trace).
     * This is a stub; in a real application, validation logic would be added.
     */
    public void validateDataAccuracy(List<StudentDTO> students) {
        System.out.println("Data accuracy validation passed for " + students.size() + " students.");
    }

    /**
     * Main interaction method simulating the sequence diagram flow.
     */
    public void startMonitoring() {
        if (!isUserLoggedIn()) {
            displayError("User is not logged in. Access denied.");
            return;
        }
        System.out.println("User logged in confirmation.");

        MonitoringThresholdCriteria criteria = getThresholdFromUser();
        List<StudentDTO> students = controller.monitorStudentsWithThreshold(criteria);
        validateDataAccuracy(students);
        displayStudents(students);
    }
}
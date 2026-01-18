package com.example.smos.view;

import com.example.smos.dto.StudentMonitoringDTO;

import java.util.List;

/**
 * Presentation layer component responsible for displaying student monitoring information and error messages.
 * This class corresponds to the 'StudentMonitoringView' class in the UML diagram.
 */
public class StudentMonitoringView {

    /**
     * Displays a list of student monitoring DTOs.
     * This method iterates through the provided list and prints each student's details.
     *
     * @param students The list of StudentMonitoringDTO objects to display.
     */
    public void displayStudents(List<StudentMonitoringDTO> students) {
        System.out.println("\n--- Student Monitoring Report ---");
        if (students.isEmpty()) {
            System.out.println("No students found meeting the specified monitoring criteria.");
        } else {
            System.out.println("Students requiring monitoring:");
            for (StudentMonitoringDTO student : students) {
                System.out.println(student);
            }
        }
        System.out.println("---------------------------------\n");
        // Simulate acknowledgment for the sequence diagram
        System.out.println("View: Student monitoring report displayed.");
    }

    /**
     * Displays an error message to the user.
     * This method is used to inform the user about system failures or issues.
     * This satisfies requirement REQ-008.
     *
     * @param message The error message to be displayed.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n--- ERROR ---");
        System.err.println("Error: " + message);
        System.err.println("-------------");
        // Simulate acknowledgment for the sequence diagram
        System.out.println("View: Error message displayed and acknowledged.");
    }
}
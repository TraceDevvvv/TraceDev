package com.school.monitoring;

import com.school.monitoring.service.StudentMonitoringService;
import com.school.monitoring.model.StudentMonitoringReportEntry;
import com.school.monitoring.data.DataSource;

import java.util.List;

/**
 * Main application class for the "Do Students Monitoring" use case.
 * This class simulates the administrator's interaction with the system
 * to query student absences and notes.
 */
public class DoStudentsMonitoringApp {

    /**
     * The main method, serving as the entry point for the application.
     * It simulates the administrator's actions and displays the monitoring report.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("--- Do Students Monitoring Application Started ---");

        // Precondition: The user is logged in as an administrator.
        // In a real application, this would involve authentication logic.
        // For this simulation, we assume the administrator is already logged in.
        System.out.println("Administrator logged in successfully.");

        // Precondition: The user clicks on the "Student Monitoring" button.
        System.out.println("Administrator clicked 'Student Monitoring' button.");

        // Step 1: Initialize the data source and monitoring service.
        // In a real application, DataSource might be injected or managed by a framework.
        DataSource dataSource = new DataSource();
        StudentMonitoringService monitoringService = new StudentMonitoringService(dataSource);

        // Define predetermined thresholds for absences and notes.
        // These could be configurable in a real system.
        final int absenceThreshold = 5; // Example: Students with more than 5 absences
        final int noteThreshold = 3;    // Example: Students with more than 3 notes

        System.out.printf("Searching for students with more than %d absences AND more than %d notes for the current school year...\n",
                absenceThreshold, noteThreshold);

        // Event Sequence 1: Search in the archive for students exceeding thresholds.
        List<StudentMonitoringReportEntry> report = monitoringService.getStudentsExceedingThresholds(
                absenceThreshold, noteThreshold
        );

        // Event Sequence 2: Displays a data presentation screen.
        System.out.println("\n--- Student Monitoring Report ---");
        if (report.isEmpty()) {
            System.out.println("No students found exceeding the specified thresholds for the current school year.");
        } else {
            System.out.println("Students exceeding thresholds:");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("%-20s %-10s %-10s %-15s\n", "Student Name", "Absences", "Notes", "School Year");
            System.out.println("---------------------------------------------------------------------------------");
            for (StudentMonitoringReportEntry entry : report) {
                System.out.printf("%-20s %-10d %-10d %-15s\n",
                        entry.getStudentName(),
                        entry.getTotalAbsences(),
                        entry.getTotalNotes(),
                        entry.getSchoolYear()
                );
            }
            System.out.println("---------------------------------------------------------------------------------");
        }

        // Postcondition: There was information about absences and notes of the students.
        // This is fulfilled by displaying the report above.

        // Postcondition: Connection to the interrupted SMOS server.
        // This simulates the end of the monitoring session or a logout.
        System.out.println("\nConnection to SMOS server interrupted (monitoring session ended).");
        System.out.println("--- Do Students Monitoring Application Finished ---");
    }
}
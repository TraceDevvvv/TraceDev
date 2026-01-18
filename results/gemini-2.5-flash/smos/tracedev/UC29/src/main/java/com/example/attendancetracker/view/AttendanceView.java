package com.example.attendancetracker.view;

import com.example.attendancetracker.model.AttendanceModel;
import com.example.attendancetracker.model.AttendanceRecord;
import com.example.attendancetracker.model.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * View class responsible for rendering attendance-related information to the Administrator.
 * <<View>> component in MVC.
 */
public class AttendanceView {

    /**
     * Renders the attendance form based on the provided model.
     * Simulates displaying a form to the administrator.
     * @param model The AttendanceModel containing data for the form.
     */
    public void render(AttendanceModel model) {
        // Formatted date for display
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = model.selectedDate != null ? sdf.format(model.selectedDate) : "N/A";

        System.out.println("\n--- Attendance Form (for " + formattedDate + ") ---");

        // Display students and their current attendance records if any
        if (model.studentsForForm != null && !model.studentsForForm.isEmpty()) {
            System.out.println("Available Students:");
            for (Student student : model.studentsForForm) {
                String currentStatus = "N/A";
                // Find existing record for this student on the selected date
                AttendanceRecord existingRecord = model.currentAttendanceRecords.stream()
                        .filter(ar -> ar.getStudentId().equals(student.getStudentId()))
                        .findFirst().orElse(null);
                if (existingRecord != null) {
                    currentStatus = existingRecord.status.name() + (existingRecord.isNotified ? " (Notified)" : "");
                }
                System.out.printf("  - ID: %s, Name: %s, Current Status: %s%n",
                                  student.getStudentId(), student.studentName, currentStatus);
            }
        } else {
            System.out.println("No students available to display.");
        }

        // Display existing/updated attendance log data
        displayUpdatedLog(model.updatedLogData, model.selectedDate);

        System.out.println("----------------------------------\n");
    }

    /**
     * Simulates displaying the updated attendance log data to the Administrator.
     * This is called after an attendance save operation.
     * @param updatedLogData The list of attendance records to display.
     * @param date The date for which the log is displayed.
     */
    public void displayUpdatedLog(List<AttendanceRecord> updatedLogData, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = date != null ? sdf.format(date) : "N/A";

        System.out.println("\n--- Updated Attendance Log for " + formattedDate + " ---");
        if (updatedLogData != null && !updatedLogData.isEmpty()) {
            updatedLogData.forEach(record -> System.out.println("  " + record));
        } else {
            System.out.println("  No attendance records for this date yet.");
        }
        System.out.println("--------------------------------------\n");
    }

    /**
     * Simulates displaying a confirmation message to the administrator.
     * @param message The message to display.
     */
    public void displayConfirmation(String message) {
        System.out.println("\n--- Administrator Message ---");
        System.out.println(message);
        System.out.println("-----------------------------\n");
    }
}
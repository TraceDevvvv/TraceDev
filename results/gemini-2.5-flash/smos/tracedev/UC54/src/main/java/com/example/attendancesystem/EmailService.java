package com.example.attendancesystem;

import java.time.LocalDate;

/**
 * Service for sending email notifications.
 * Defined in the Infrastructure layer of the Class Diagram.
 */
public class EmailService {
    /**
     * Sends a parent notification about a student's attendance status.
     * @param student The student for whom the notification is sent.
     * @param date The date of the attendance.
     * @param status The attendance status (e.g., ABSENT).
     */
    public void sendParentNotification(Student student, LocalDate date, AttendanceStatus status) {
        System.out.println("[EmailService] Sending notification to " + student.getParentEmail() +
                           " for student " + student.getName() +
                           " on " + date + ": Status " + status);
        // Simulate email sending delay
        try { Thread.sleep(30); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
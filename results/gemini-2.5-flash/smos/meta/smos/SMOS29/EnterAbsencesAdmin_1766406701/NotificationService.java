package com.example.absencesystem;

import java.time.format.DateTimeFormatter;

/**
 * Service responsible for sending notifications, particularly email notifications
 * to parents regarding student absences or delays.
 * In a real-world application, this would integrate with an actual email sending API.
 * For this simulation, it will log the notification attempt.
 */
public class NotificationService {

    // DateTimeFormatter for consistent date formatting in notifications
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Sends an absence or delay notification email to the parent of the specified student.
     * This method simulates the email sending process by printing the notification details
     * to the console.
     *
     * @param student The student for whom the absence/delay is recorded. Must not be null.
     * @param absence The absence or delay record. Must not be null.
     * @throws IllegalArgumentException if student or absence is null.
     */
    public void sendAbsenceNotification(Student student, Absence absence) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null for sending notification.");
        }
        if (absence == null) {
            throw new IllegalArgumentException("Absence record cannot be null for sending notification.");
        }

        // Construct the email subject and body
        String subject = String.format("Notification: Student %s - %s on %s",
                                       student.getName(),
                                       absence.getType().toString().toLowerCase(),
                                       absence.getDate().format(DATE_FORMATTER));

        StringBuilder body = new StringBuilder();
        body.append(String.format("Dear %s's Parent/Guardian,\n\n", student.getName()));
        body.append(String.format("This is an automated notification regarding your child, %s (ID: %s).\n",
                                   student.getName(), student.getStudentId()));
        body.append(String.format("We would like to inform you that %s was recorded as %s on %s.\n",
                                   student.getName(),
                                   absence.getType().toString().toLowerCase(),
                                   absence.getDate().format(DATE_FORMATTER)));

        if (absence.getReason() != null) {
            body.append(String.format("Reason provided: %s\n", absence.getReason()));
        } else {
            body.append("No specific reason was provided.\n");
        }

        body.append("\nPlease contact the school administration if you have any questions.\n");
        body.append("Sincerely,\nThe School Administration");

        // Simulate sending the email
        System.out.println("--- Simulating Email Notification ---");
        System.out.println("To: " + student.getParentEmail());
        System.out.println("Subject: " + subject);
        System.out.println("Body:\n" + body.toString());
        System.out.println("-------------------------------------\n");

        // In a real system, you would use an email library here, e.g.:
        // EmailSender.sendEmail(student.getParentEmail(), subject, body.toString());
    }
}